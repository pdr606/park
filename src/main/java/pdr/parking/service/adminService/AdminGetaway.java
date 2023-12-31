package pdr.parking.service.adminService;

import org.w3c.dom.stylesheets.LinkStyle;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.Park;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.service.userService.UserGateway;

import java.io.IOException;
import java.util.List;

public interface AdminGetaway{
    List<User> findAllUsers();
    User findUserByCpf(String cpf);
    Vehicle findVehicleByPlate(String plate);
    List<Vehicle> findAllVehicles();
    List<Park> findAllParks();
    void createTrafficGuard(UserCreateRequestDto userCreateRequestDto) throws IOException;
    void deleteTrafficGuard(Long id);
}
