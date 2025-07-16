package com.challenge.resilient_payment_router.dtos;

import java.time.Instant;
import java.util.UUID;

public record PaymentRequestDTO(UUID correlationId, Double amount, String requestedAt) {
    public static PaymentRequestDTO converter(PaymentClientDTO payment){
        return new PaymentRequestDTO(
                payment.correlationId(),
                payment.amount(),
                Instant.now().toString());
    }
}
