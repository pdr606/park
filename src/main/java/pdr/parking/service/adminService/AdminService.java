package pdr.parking.service.adminService;

import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.service.userService.UserGateway;
import pdr.parking.service.vehicleService.VehicleGateway;

import java.util.List;

@Service
public class AdminService implements AdminGetaway {

    private UserGateway userGateway;
    private VehicleGateway vehicleGateway;

    public AdminService(UserGateway userGateway, VehicleGateway vehicleGateway)
    {
        this.userGateway = userGateway;
        this.vehicleGateway = vehicleGateway;
    }
    @Override
    public List<UserResponseDto> findAllUsers() {
        return userGateway.findAll();
    }

    @Override
    public User findUserByCpf(String cpf) {
        return userGateway.findByCpf(cpf);
    }

    @Override
    public VehicleResponseDto findVehicleByPlate(String plate) {
        return vehicleGateway.findByPlate(plate);
    }

    @Override
    public List<VehicleResponseDto> findAllVehicles() {
        return vehicleGateway.findAll();
    }
}
