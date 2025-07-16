package com.challenge.resilient_payment_router.exceptions;

public class ProcessorUnavailableException extends RuntimeException {
    public ProcessorUnavailableException(String message) {
        super(message);
    }
}