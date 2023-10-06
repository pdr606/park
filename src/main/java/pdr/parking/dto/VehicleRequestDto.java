package pdr.parking.dto;

import pdr.parking.entities.enums.VehicleType;

public record VehicleRequestDto(
        String plate,
        boolean foreignPlate,
        String brand,
        String model,
        VehicleType vehicleType,
        Long userId
)
{}
