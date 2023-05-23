package com.smartbusinessbackend.smartbusinesscard.stripe.service;

import com.smartbusinessbackend.smartbusinesscard.stripe.PaymentDTO;

public interface StripeService {

    String executePayment(PaymentDTO paymentDTO);
}
