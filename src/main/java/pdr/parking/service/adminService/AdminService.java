package pdr.parking.service.adminService;

import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.Park;
import pdr.parking.entities.User;
import pdr.parking.entities.enums.Role;
import pdr.parking.service.parkService.ParkGateway;
import pdr.parking.service.userService.UserGateway;
import pdr.parking.service.vehicleService.VehicleGateway;

import java.io.IOException;
import java.util.List;

@Service
public class AdminService implements AdminGetaway {

    private final UserGateway userGateway;
    private final VehicleGateway vehicleGateway;
    private final ParkGateway parkGateway;

    public AdminService(UserGateway userGateway, VehicleGateway vehicleGateway,
                        ParkGateway parkGateway)
    {
        this.userGateway = userGateway;
        this.vehicleGateway = vehicleGateway;
        this.parkGateway = parkGateway;
    }
    @Override
    public List<UserResponseDto> findAllUsers() {
        return userGateway.findAll();
    }

    @Override
    public UserResponseDto findUserByCpf(String cpf) {
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

    @Override
    public List<Park> findAllParks() {
        return parkGateway.findAll();
    }

    @Override
    public void createTrafficGuard(UserCreateRequestDto userCreateRequestDto) throws IOException {
        userCreateRequestDto = new UserCreateRequestDto(
                userCreateRequestDto.firstName(),
                userCreateRequestDto.lastName(),
                userCreateRequestDto.cpf(),
                userCreateRequestDto.email(),
                userCreateRequestDto.telephone(),
                userCreateRequestDto.password(),
                Role.TRAFFIC_GUARD,
                userCreateRequestDto.image()
        );
        userGateway.createUser(userCreateRequestDto);
    }

    @Override
    public void deleteTrafficGuard(Long id) {
        userGateway.deleteUser(id);
    }
}
