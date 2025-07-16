package com.challenge.resilient_payment_router.services;

import com.challenge.resilient_payment_router.dtos.PaymentRequestDTO;
import com.challenge.resilient_payment_router.dtos.PaymentClientDTO;
import com.challenge.resilient_payment_router.exceptions.ProcessorUnavailableException;
import com.challenge.resilient_payment_router.processors.DefaultProcessorClient;
import com.challenge.resilient_payment_router.processors.FallbackProcessorClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private DefaultProcessorClient defaultProcessor;
    @Autowired
    private FallbackProcessorClient fallbackProcessor;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    @Autowired
    private ProcessorHealthService processorHealthService;

//    @Retry(name="paymentProcessorRetry")
//    @CircuitBreaker(name = "paymentProcessor", fallbackMethod = "fallbackPayment")
//    public String routePayment(PaymentClientDTO payment){
//        PaymentRequestDTO request = PaymentRequestDTO.converter(payment);
//        return testDefault(request);
//    }
//
//    public String testDefault(PaymentRequestDTO request){
//        if(!processorHealthService.isDefaultHealthy())
//            throw new ProcessorUnavailableException("Processor unhealthly!");
//        return defaultProcessor.processPayment(request);
//    }
//
//    public String fallbackPayment(PaymentClientDTO payment, Throwable t){
//        PaymentRequestDTO request = PaymentRequestDTO.converter(payment);
//        System.out.println("Tentando Fallback");
//        return fallbackProcessor.processPayment(request);
//    }

    public String routePayment(PaymentClientDTO payment){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("paymentProcessor");
        PaymentRequestDTO request = PaymentRequestDTO.converter(payment);
        return circuitBreaker.run(() ->
                {
                    if (processorHealthService.isDefaultHealthy()) {
                        return defaultProcessor.processPayment(request);
                    }
                    throw new ProcessorUnavailableException("Default processor unstable");
                },
            throwable -> fallbackProcessor.processPayment(request));
    }
}
