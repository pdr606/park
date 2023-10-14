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
import pdr.parking.mapper.VehicleMapper;
import pdr.parking.repository.VehicleRepository;
import pdr.parking.service.userService.UserGateway;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService implements VehicleGateway {


    private final VehicleRepository vehicleRepository;
    private final UserGateway userGateway;


    public VehicleService(VehicleRepository vehicleRepository, UserGateway userGateway) {
        this.vehicleRepository = vehicleRepository;
        this.userGateway = userGateway;
    }

    @Override
    public void registerVehicle(VehicleRequestDto vehicleRequestDto) {
        User user = userGateway.findById(vehicleRequestDto.userId());
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
                .orElseThrow(UserNotFoundException::new
                );

    }

    @Override
    public void deleteVehicle(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle findByPlate(String plate) {
        try{
            return vehicleRepository.findByPlate(plate);
        } catch (DataIntegrityViolationException ex){
            throw new VehicleNotFoundException();
        }
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}
