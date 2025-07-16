package com.challenge.resilient_payment_router.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentsSummaryDTO(@JsonProperty("default") ProcessorPaymentsSummaryDTO defaultProcessor, ProcessorPaymentsSummaryDTO fallback) {
}
