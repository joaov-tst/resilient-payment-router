package com.challenge.resilient_payment_router.processors;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "defaultProcessor", url = "${processor.default.url}")
public interface DefaultProcessorClient extends ProcessorClient{
}
