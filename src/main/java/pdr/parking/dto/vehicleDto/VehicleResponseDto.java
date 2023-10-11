package pdr.parking.dto.vehicleDto;

import pdr.parking.entities.User;
import pdr.parking.entities.enums.VehicleType;

public record VehicleResponseDto(
        String plate,
        boolean foreignPlate,
        String brand,
        String model,
        VehicleType vehicleType
) {
}
