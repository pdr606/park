package pdr.parking.service.vehicleService;

import pdr.parking.dto.VehicleRequestDto;
import pdr.parking.entities.Vehicle;

public interface VehicleGateway {

    void registerVehicle(VehicleRequestDto vehicleRequestDto);

    Vehicle findById(Long id);
}
