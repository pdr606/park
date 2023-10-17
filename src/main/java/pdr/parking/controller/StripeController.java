package pdr.parking.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.stripeDto.PaymentRequestChargeDto;
import pdr.parking.dto.stripeDto.PaymentRequestGenerateTokenDto;
import pdr.parking.dto.stripeDto.PaymentResponseTokenDto;
import pdr.parking.mapper.StripeMapper;
import pdr.parking.service.paymentService.stripeService.StripeService;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class StripeController {

    private final StripeService stripeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/card/token")
    public PaymentResponseTokenDto createCardToken(@RequestBody PaymentRequestGenerateTokenDto stripeTokenDto){
        return StripeMapper.toToken(stripeService.createCardToken(stripeTokenDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/charge")
    public PaymentRequestChargeDto charge(@RequestBody PaymentRequestChargeDto stripeChargeDto){
        return stripeService.charge(stripeChargeDto);
    }
}
