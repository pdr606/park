package pdr.parking.service.vehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdr.parking.dto.VehicleRequestDto;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.exceptions.UserNotFoundException;
import pdr.parking.mapper.VehicleMapper;
import pdr.parking.repository.VehicleRepository;
import pdr.parking.service.userService.UserService;

@Service
public class VehicleService implements VehicleGateway {

    @Autowired
    private final VehicleRepository vehicleRepository;

    @Autowired
    private final UserService userService;

    public VehicleService(VehicleRepository vehicleRepository, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
    }

    @Override
    public void registerVehicle(VehicleRequestDto vehicleRequestDto) {
        User user = userService.findById(vehicleRequestDto.userId());
        vehicleRepository.save(VehicleMapper.toEntity(vehicleRequestDto, user));
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException()
                );

    }
}
