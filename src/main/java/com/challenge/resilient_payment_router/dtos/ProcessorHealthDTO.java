package com.challenge.resilient_payment_router.dtos;

public record ProcessorHealthDTO(boolean failing, double minResponseTime) {
    public boolean isHealthy() {
        return failing == false && minResponseTime < 500;
    }

}
