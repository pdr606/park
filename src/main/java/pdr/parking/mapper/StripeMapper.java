package pdr.parking.mapper;

import pdr.parking.dto.stripeDto.PaymentRequestGenerateTokenDto;
import pdr.parking.dto.stripeDto.PaymentResponseTokenDto;

public class StripeMapper {

    public static PaymentResponseTokenDto toToken(PaymentRequestGenerateTokenDto stripeRequestGenerateTokenDto){
        return new PaymentResponseTokenDto(
                stripeRequestGenerateTokenDto.getToken()
        );
    }
}
