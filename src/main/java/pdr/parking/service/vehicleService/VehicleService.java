package pdr.parking.service.vehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pdr.parking.dto.vehicleDto.VehicleRequestDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.exceptions.UserNotFoundException;
import pdr.parking.exceptions.VehicleNotFoundException;
import pdr.parking.mapper.UserMapper;
import pdr.parking.mapper.VehicleMapper;
import pdr.parking.repository.VehicleRepository;
import pdr.parking.service.userService.UserService;

import java.util.List;
import java.util.Optional;

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
        User user = UserMapper.toEntity(userService.findById(vehicleRequestDto.userId()));
        vehicleRepository.save(new Vehicle(vehicleRequestDto.plate(),
                vehicleRequestDto.foreignPlate(),
                vehicleRequestDto.brand(),
                vehicleRequestDto.model(),
                vehicleRequestDto.vehicleType(),
                user));
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException()
                );

    }

    @Override
    public void deleteVehicle(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        vehicleRepository.deleteById(id);
    }

    @Override
    public VehicleResponseDto findByPlate(String plate) {
        try{
            return VehicleMapper.toResponse(vehicleRepository.findByPlate(plate));
        } catch (DataIntegrityViolationException ex){
            throw new VehicleNotFoundException();
        }
    }

    @Override
    public List<VehicleResponseDto> findAll() {
        return VehicleMapper.toResponse(vehicleRepository.findAll());
    }
}
