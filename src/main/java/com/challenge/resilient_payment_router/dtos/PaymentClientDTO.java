package com.challenge.resilient_payment_router.dtos;

import java.util.UUID;

public record PaymentClientDTO(UUID correlationId, Double amount) {
}
