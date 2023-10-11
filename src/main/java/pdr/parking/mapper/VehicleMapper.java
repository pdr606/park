package pdr.parking.mapper;

import org.mapstruct.Mapper;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class VehicleMapper {

    public static List<VehicleResponseDto> toResponse(List<Vehicle> vehicles){
        return
                vehicles.stream().map(vehicle ->
                        new VehicleResponseDto(
                                vehicle.getPlate(),
                                vehicle.isForeignPlate(),
                                vehicle.getBrand(),
                                vehicle.getModel(),
                                vehicle.getVehicleType()
                        )).collect(Collectors.toList());
    }

    public static VehicleResponseDto toResponse(Vehicle vehicle){
        return
                new VehicleResponseDto(
                        vehicle.getPlate(),
                        vehicle.isForeignPlate(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getVehicleType());
    }
}
