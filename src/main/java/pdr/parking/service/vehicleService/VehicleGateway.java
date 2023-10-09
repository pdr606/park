package pdr.parking.service.vehicleService;

import pdr.parking.dto.vehicleDto.VehicleRequestDto;
import pdr.parking.entities.Vehicle;

public interface VehicleGateway {

    void registerVehicle(VehicleRequestDto vehicleRequestDto);

    Vehicle findById(Long id);

    void deleteVehicle(Long id);
}
