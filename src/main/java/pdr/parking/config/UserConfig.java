package pdr.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import pdr.parking.mapper.ParkMapper;
import pdr.parking.mapper.StripeMapper;
import pdr.parking.mapper.UserDtoMapper;
import pdr.parking.mapper.VehicleMapper;
import pdr.parking.service.paymentService.stripeService.StripeService;

@Configuration
public class UserConfig {

    @Bean
    UserDtoMapper userMapper(){
        return new UserDtoMapper();
    }

    @Bean
    VehicleMapper vehicleMapper(){
        return  new VehicleMapper();
    }

    @Bean
    ParkMapper parkerMapper(){
        return new ParkMapper();
    }


}
