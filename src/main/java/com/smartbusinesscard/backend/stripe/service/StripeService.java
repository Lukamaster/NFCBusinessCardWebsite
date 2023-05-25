package com.smartbusinesscard.backend.stripe.service;

import com.smartbusinesscard.backend.stripe.PaymentDTO;
import com.stripe.exception.StripeException;

public interface StripeService {

    String executePayment(PaymentDTO paymentDTO) throws StripeException;
}
