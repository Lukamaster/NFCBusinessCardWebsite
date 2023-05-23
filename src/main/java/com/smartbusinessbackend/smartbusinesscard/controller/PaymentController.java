package com.smartbusinessbackend.smartbusinesscard.controller;

import com.smartbusinessbackend.smartbusinesscard.stripe.PaymentDTO;
import com.smartbusinessbackend.smartbusinesscard.stripe.service.StripeService;
import com.stripe.model.Charge;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final StripeService stripeService;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody PaymentDTO paymentDTO){
        String chargeStatus = stripeService.executePayment(paymentDTO);
        return ResponseEntity.ok(chargeStatus);
    }
}
