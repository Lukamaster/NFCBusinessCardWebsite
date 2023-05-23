package com.smartbusinessbackend.smartbusinesscard.stripe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbusinessbackend.smartbusinesscard.config.PurchaseConfig;
import com.smartbusinessbackend.smartbusinesscard.model.AppUser;
import com.smartbusinessbackend.smartbusinesscard.model.PurchasedCard;
import com.smartbusinessbackend.smartbusinesscard.service.UserService;
import com.smartbusinessbackend.smartbusinesscard.stripe.PaymentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeServiceImpl implements StripeService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final Binding binding;
    private final PurchaseConfig purchaseConfig;

    @Override
    @Transactional
    public String executePayment(PaymentDTO paymentDTO) {
        //createCustomer(paymentDTO);
        Stripe.apiKey = purchaseConfig.getSecretApiKey();
        //ChargeCreateParams chargeCreateParams = getChargeCreateParams(paymentDTO);
        Map<String,Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentDTO.getAmount());
        chargeParams.put("currency", paymentDTO.getCurrency());
        //TODO: finish this payment process, with all the right DTO's and models
        chargeParams.put("source", "tok_visa");
        try {
            Charge charge = Charge.create(chargeParams);
            convertAndSendCustomer(card(paymentDTO.getEmail()));
            return charge.getStatus();
        } catch (StripeException | JsonProcessingException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private void createCustomer(PaymentDTO paymentDTO) {
        CustomerCreateParams.builder().setName(paymentDTO.getCustomerFullName()).setEmail(paymentDTO.getEmail()).build();
    }

    private ChargeCreateParams getChargeCreateParams(PaymentDTO paymentDTO) {
        return new ChargeCreateParams.
                Builder().
                setAmount(paymentDTO.getAmount().longValue()).
                setCurrency(paymentDTO.getCurrency().toString()).
                setReceiptEmail(paymentDTO.getEmail()).
                setDescription(paymentDTO.getDescription()).
                setCustomer("user@gmail.com").build();
    }

    private void convertAndSendCustomer(PurchasedCard card) throws JsonProcessingException {
        String jsonData = objectMapper.writeValueAsString(card);
        try {
            rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), jsonData);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private PurchasedCard card(String userEmail) {
        AppUser user = userService.findByEmail(userEmail);
        String url = purchaseConfig.getBaseUrl() + user.getId();
        return PurchasedCard.builder().appUser(user).generatedUrl(url).build();
    }
}
