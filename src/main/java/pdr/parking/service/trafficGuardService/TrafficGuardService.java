package pdr.parking.service.trafficGuardService;

import org.springframework.stereotype.Service;
import pdr.parking.service.parkService.ParkGateway;
import pdr.parking.service.trafficTicketService.TrafficTicketService;

@Service
public class TrafficGuardService implements TrafficGuardGetaway {


    private ParkGateway parkGateway;

    public TrafficGuardService(ParkGateway parkGateway) {
        this.parkGateway = parkGateway;
    }

    @Override
    public void checkPlate(String plate) {
        parkGateway.checkPlate(plate);
    }
}
