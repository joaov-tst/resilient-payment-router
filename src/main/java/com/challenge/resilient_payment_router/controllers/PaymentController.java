package com.challenge.resilient_payment_router.controllers;

import com.challenge.resilient_payment_router.dtos.PaymentClientDTO;
import com.challenge.resilient_payment_router.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> payment(@RequestBody PaymentClientDTO paymentClient){
        return ResponseEntity.ok(paymentService.routePayment(paymentClient));
    }
}
