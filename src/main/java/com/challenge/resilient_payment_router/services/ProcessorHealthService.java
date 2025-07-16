package com.challenge.resilient_payment_router.services;

import com.challenge.resilient_payment_router.dtos.ProcessorHealthDTO;
import com.challenge.resilient_payment_router.processors.DefaultProcessorClient;
import com.challenge.resilient_payment_router.processors.FallbackProcessorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProcessorHealthService {
    @Autowired
    private DefaultProcessorClient defaultProcessor;
    @Autowired
    private FallbackProcessorClient fallbackProcessor;

    private boolean isDefaultHealthy = true;
    private boolean isFallbackHealthy = true;
    private Long lastUpdated;

    public boolean isStatusRecent() {
        return (System.currentTimeMillis() - (lastUpdated != null? lastUpdated : 0)) < 3000;
    }

    @Scheduled(fixedDelayString = "${processor.healthcheck.interval}")
    public void checkAllProcessors() {
        try {
            ProcessorHealthDTO processorHealthDTO = defaultProcessor.checkHealth();
            isDefaultHealthy = processorHealthDTO.isHealthy();
        } catch (Exception e) {
            isDefaultHealthy = false;
        }
        try {
            isFallbackHealthy = fallbackProcessor.checkHealth().isHealthy();
        } catch (Exception e) {
            isFallbackHealthy = false;
        }finally {
            System.out.println("Checando healthy: " + isDefaultHealthy + ", " + isFallbackHealthy);
            this.lastUpdated = System.currentTimeMillis();
        }
    }



    public boolean isDefaultHealthy() { return isDefaultHealthy; }
    public boolean isFallbackHealthy() { return isFallbackHealthy; }
}
