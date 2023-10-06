package pdr.parking.mapper;

import org.mapstruct.Mapper;
import pdr.parking.dto.VehicleRequestDto;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;

@Mapper
public class VehicleMapper {

    public static Vehicle toEntity(VehicleRequestDto vehicleRequestDto, User user){
        return new Vehicle(
                null,
                vehicleRequestDto.plate(),
                vehicleRequestDto.foreignPlate(),
                vehicleRequestDto.brand(),
                vehicleRequestDto.model(),
                vehicleRequestDto.vehicleType(),
                user,
                null
        );
    }
}
