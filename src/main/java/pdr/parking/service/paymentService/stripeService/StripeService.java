package pdr.parking.service.paymentService.stripeService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pdr.parking.dto.stripeDto.PaymentRequestChargeDto;
import pdr.parking.dto.stripeDto.PaymentRequestGenerateTokenDto;
import pdr.parking.service.paymentService.PaymentGetaway;
import pdr.parking.service.userService.UserGateway;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j

public class StripeService implements PaymentGetaway {

    @Value("${api.stripe.key}")
    private String stripeApiKey;
    private UserGateway userGateway;
    public StripeService(UserGateway userGateway){
        this.userGateway = userGateway;
    }

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public PaymentRequestGenerateTokenDto createCardToken(PaymentRequestGenerateTokenDto model) {

        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpYear()));
            card.put("cvc", model.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                model.setSuccess(true);
                model.setToken(token.getId());
            }
            return model;
        } catch (StripeException e) {
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException(e.getMessage());
        }

    }
    @Override
    public PaymentRequestChargeDto charge(PaymentRequestChargeDto chargeRequest) {

        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "BRL");
            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("USER_ID", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);
                userGateway.addBalance(chargeRequest.getUserId(), Math.toIntExact(charge.getAmount()));
            }
            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }

    }


}
