package com.challenge.resilient_payment_router.processors;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "fallbackProcessor", url = "${processor.fallback.url}")
public interface FallbackProcessorClient extends ProcessorClient {
}
