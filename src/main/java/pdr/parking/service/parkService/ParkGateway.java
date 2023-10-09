package pdr.parking.service.parkService;

import pdr.parking.dto.parkDto.ParkingRequestDto;
import pdr.parking.entities.Park;

public interface ParkGateway {

    Park generatePark(ParkingRequestDto parkingRequestDto);
}
