package pdr.parking.service.adminService;

import org.w3c.dom.stylesheets.LinkStyle;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.entities.User;
import pdr.parking.service.userService.UserGateway;

import java.util.List;

public interface AdminGetaway{

    List<UserResponseDto> findAllUsers();
}
