package pdr.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pdr.parking.mapper.ParkMapper;
import pdr.parking.mapper.UserDtoMapper;
import pdr.parking.mapper.VehicleMapper;

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
