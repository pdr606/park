package pdr.parking.dto.stripeDto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PaymentRequestChargeDto {

    private  String stripeToken;
    private String username;
    private Double amount;
    private Boolean success;
    private String message;
    private String chargeId;
    private Long userId;
    private Map<String, Object> additionalInfo = new HashMap<>();
}
