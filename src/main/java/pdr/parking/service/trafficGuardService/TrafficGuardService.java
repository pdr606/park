package pdr.parking.service.trafficGuardService;

import org.springframework.stereotype.Service;
import pdr.parking.entities.TrafficTicket;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.service.parkService.ParkGateway;
import pdr.parking.service.trafficTicketService.TrafficTicketGetaway;
import pdr.parking.service.userService.UserGateway;

@Service
public class TrafficGuardService implements TrafficGuardGetaway {
    private final ParkGateway parkGateway;
    private final UserGateway userGateway;
    private final TrafficTicketGetaway trafficTicketGetaway;
    public TrafficGuardService(ParkGateway parkGateway,
                               UserGateway userGateway,
                               TrafficTicketGetaway trafficTicketGetaway)
    {
        this.parkGateway = parkGateway;
        this.userGateway = userGateway;
        this.trafficTicketGetaway = trafficTicketGetaway;
    }
    @Override
    public void checkPlate(String plate) {
        parkGateway.checkPlate(plate);
    }


    @Override
    public TrafficTicket generateTrafficTicket(String plate) {
        User user = userGateway.findByVehiclePlate(plate);
        return trafficTicketGetaway.generateTrafficTicket(user, (Vehicle) user.getVehicles());

    }
}
