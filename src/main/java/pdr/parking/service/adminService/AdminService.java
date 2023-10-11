package pdr.parking.service.adminService;

import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.entities.User;
import pdr.parking.service.userService.UserGateway;

import java.util.List;

@Service
public class AdminService implements AdminGetaway {

    private UserGateway userGateway;

    public AdminService(UserGateway userGateway){
        this.userGateway = userGateway;
    }
    @Override
    public List<UserResponseDto> findAllUsers() {
        return userGateway.findAll();
    }
}
