package pdr.parking.service.paymentService;

import pdr.parking.dto.stripeDto.PaymentRequestChargeDto;
import pdr.parking.dto.stripeDto.PaymentRequestGenerateTokenDto;

public interface PaymentGetaway {

    PaymentRequestGenerateTokenDto createCardToken(PaymentRequestGenerateTokenDto model);

    PaymentRequestChargeDto charge(PaymentRequestChargeDto model);

}
