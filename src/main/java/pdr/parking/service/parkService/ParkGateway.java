package pdr.parking.service.parkService;

import pdr.parking.dto.parkDto.ParkingRequestDto;
import pdr.parking.entities.Park;

import java.util.List;

public interface ParkGateway {
    Park generatePark(ParkingRequestDto parkingRequestDto);
    void checkExpiration();
    boolean checkPlate(String plate);
    List<Park> findAll();
}
