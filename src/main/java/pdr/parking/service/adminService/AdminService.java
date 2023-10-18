package pdr.parking.service.adminService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.Park;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.entities.enums.Role;
import pdr.parking.service.parkService.ParkGateway;
import pdr.parking.service.userService.UserGateway;
import pdr.parking.service.vehicleService.VehicleGateway;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService implements AdminGetaway {

    private final UserGateway userGateway;
    private final VehicleGateway vehicleGateway;
    private final ParkGateway parkGateway;

    @Override
    public List<User> findAllUsers() {
        return userGateway.findAll();
    }

    @Override
    public User findUserByCpf(String cpf) {
        return userGateway.findByCpf(cpf);
    }

    @Override
    public Vehicle findVehicleByPlate(String plate) {
        return vehicleGateway.findByPlate(plate);
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return vehicleGateway.findAll();
    }

    @Override
    public List<Park> findAllParks() {
        return parkGateway.findAll();
    }

    @Override
    public void createTrafficGuard(UserCreateRequestDto userCreateRequestDto) throws IOException {
        userCreateRequestDto = new UserCreateRequestDto(
                userCreateRequestDto.getFirstName(),
                userCreateRequestDto.getLastName(),
                userCreateRequestDto.getCpf(),
                userCreateRequestDto.getEmail(),
                userCreateRequestDto.getTelephone(),
                userCreateRequestDto.getPassword(),
                Role.TRAFFIC_GUARD
        );
        userGateway.createUser(userCreateRequestDto);
    }

    @Override
    public void deleteTrafficGuard(Long id) {
        userGateway.deleteUser(id);
    }
}
