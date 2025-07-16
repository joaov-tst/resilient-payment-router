package com.challenge.resilient_payment_router.processors;

import com.challenge.resilient_payment_router.dtos.PaymentRequestDTO;
import com.challenge.resilient_payment_router.dtos.ProcessorHealthDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/payments")
public interface ProcessorClient {
    @PostMapping()
    public String processPayment(@RequestBody PaymentRequestDTO request);

    @GetMapping("/service-health")
    public ProcessorHealthDTO checkHealth();
}
