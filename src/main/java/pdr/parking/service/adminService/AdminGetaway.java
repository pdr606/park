package pdr.parking.service.adminService;

import org.w3c.dom.stylesheets.LinkStyle;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.service.userService.UserGateway;

import java.util.List;

public interface AdminGetaway{

    List<UserResponseDto> findAllUsers();

    User findUserByCpf(String cpf);

    VehicleResponseDto findVehicleByPlate(String plate);

    List<VehicleResponseDto> findAllVehicles();

    void createTrafficGuard(UserCreateRequestDto userCreateRequestDto);
}
