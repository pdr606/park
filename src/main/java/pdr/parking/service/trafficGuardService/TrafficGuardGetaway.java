package pdr.parking.service.trafficGuardService;

import pdr.parking.entities.TrafficTicket;
import pdr.parking.service.trafficTicketService.TrafficTicketGetaway;

public interface TrafficGuardGetaway {
    void checkPlate(String plate);

    TrafficTicket generateTrafficTicket(String plate);

}
