package com.smartbusinesscard.backend.stripe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbusinesscard.backend.config.PurchaseConfig;
import com.smartbusinesscard.backend.config.rabbitmq.RabbitMQConfigParams;
import com.smartbusinesscard.backend.model.AppUser;
import com.smartbusinesscard.backend.model.BusinessCard;
import com.smartbusinesscard.backend.model.mapper.BusinessCardMapper;
import com.smartbusinesscard.backend.service.UserService;
import com.smartbusinesscard.backend.stripe.PaymentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeServiceImpl implements StripeService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final RabbitMQConfigParams rabbitMQConfigParams;
    private final PurchaseConfig purchaseConfig;
    private final BusinessCardMapper businessCardMapper;

    @Override
    @Transactional
    public String executePayment(PaymentDTO paymentDTO) {
        Stripe.apiKey = purchaseConfig.getSecretApiKey();
        Customer customer = createCustomer(paymentDTO);
        ChargeCreateParams chargeCreateParams = getChargeCreateParams(paymentDTO, customer);
        try {
            Charge charge = Charge.create(chargeCreateParams);
            sendToQueueBusinessCardDetails(generateBusinessCardDetails(paymentDTO.getEmail()));
            return charge.getStatus();
        } catch (StripeException | JsonProcessingException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private ChargeCreateParams getChargeCreateParams(PaymentDTO paymentDTO, Customer customer) {
        return ChargeCreateParams.builder().setCustomer(customer.getId()).setReceiptEmail(customer.getEmail())
                .setCurrency(paymentDTO.getCurrency().toString()).setAmount(paymentDTO.getAmount().longValue()).build();
    }

    private Customer createCustomer(PaymentDTO paymentDTO) {
        CustomerCreateParams customerCreateParams = CustomerCreateParams.builder().
                setName(paymentDTO.getCustomerFullName()).setEmail(paymentDTO.getEmail()).
                setSource(purchaseConfig.getCardToken()).build();
        try {
            return Customer.create(customerCreateParams);
        } catch (StripeException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sends the serialized JSON data for a newly created business card POJO to a specified queue on the RabbitMQ server.
     *
     * @param card the business card POJO with the assigned values regarding the purchase of the customer.
     * @throws JsonProcessingException if the serializing didn't complete with success.
     */
    private void sendToQueueBusinessCardDetails(BusinessCard card) throws JsonProcessingException {
        String jsonData = objectMapper.writeValueAsString(card);
        try {
            rabbitTemplate.convertAndSend(rabbitMQConfigParams.getExchange(), rabbitMQConfigParams.getRoutingKey(), jsonData);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates the business card POJO regarding the user email.
     *
     * @param userEmail the user's email address.
     * @return the created POJO.
     */
    private BusinessCard generateBusinessCardDetails(String userEmail) {
        if (StringUtils.isEmpty(userEmail)) {
            log.error("User mail is null, cannot create business card dto.");
            throw new IllegalArgumentException();
        }
        AppUser user = userService.findByEmail(userEmail);
        BusinessCard businessCard = businessCardMapper.mapBusinessCardForUser(user);
        String url = purchaseConfig.getBaseUrl() + user.getId();
        businessCard.setGeneratedUrl(url);
        return businessCard;
    }
}
