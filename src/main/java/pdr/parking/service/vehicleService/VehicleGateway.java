package pdr.parking.service.vehicleService;

import pdr.parking.dto.vehicleDto.VehicleRequestDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.Vehicle;

import java.util.List;

public interface VehicleGateway {

    void registerVehicle(VehicleRequestDto vehicleRequestDto);

    Vehicle findById(Long id);

    void deleteVehicle(Long id);

    Vehicle findByPlate(String plate);

    List<Vehicle> findAll();
}
