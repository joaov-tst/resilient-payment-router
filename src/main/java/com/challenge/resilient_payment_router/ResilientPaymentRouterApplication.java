package com.challenge.resilient_payment_router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.challenge.resilient_payment_router.processors")
@EnableScheduling
public class ResilientPaymentRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResilientPaymentRouterApplication.class, args);
	}

}
