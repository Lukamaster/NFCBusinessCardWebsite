package com.smartbusinesscard.backend.controller;

import com.smartbusinesscard.backend.stripe.PaymentDTO;
import com.smartbusinesscard.backend.stripe.service.StripeService;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@SecurityRequirement(name = "securityScheme")
public class PaymentController {

    private final StripeService stripeService;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody PaymentDTO paymentDTO) throws StripeException {
        String chargeStatus = stripeService.executePayment(paymentDTO);
        return ResponseEntity.ok(chargeStatus);
    }
}
