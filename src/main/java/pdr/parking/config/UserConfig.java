package pdr.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pdr.parking.mapper.UserMapper;

@Configuration
public class UserConfig {

    @Bean
    UserMapper userMapper(){
        return new UserMapper();
    }
}
