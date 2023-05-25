package com.smartbusinessbackend.smartbusinesscard.stripe.service;

import com.smartbusinessbackend.smartbusinesscard.stripe.PaymentDTO;
import com.stripe.exception.StripeException;

public interface StripeService {

    String executePayment(PaymentDTO paymentDTO) throws StripeException;
}
