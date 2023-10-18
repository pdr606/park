package pdr.parking.service.trafficGuardService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.parking.entities.TrafficTicket;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.service.parkService.ParkGateway;
import pdr.parking.service.trafficTicketService.TrafficTicketGetaway;
import pdr.parking.service.userService.UserGateway;

@Service
@AllArgsConstructor
public class TrafficGuardService implements TrafficGuardGetaway {

    private final ParkGateway parkGateway;
    private final UserGateway userGateway;
    private final TrafficTicketGetaway trafficTicketGetaway;
    @Override
    public boolean checkPlate(String plate) {
        return parkGateway.checkPlate(plate);
    }

    @Override
    public TrafficTicket generateTrafficTicket(String plate) {
        User user = userGateway.findByVehiclePlate(plate);
        return trafficTicketGetaway.generateTrafficTicket(user, (Vehicle) user.getVehicles());

    }
}
