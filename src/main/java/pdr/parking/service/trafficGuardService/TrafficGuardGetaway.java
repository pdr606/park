package pdr.parking.service.trafficGuardService;

import pdr.parking.entities.TrafficTicket;
import pdr.parking.service.trafficTicketService.TrafficTicketGetaway;

public interface TrafficGuardGetaway {
    boolean checkPlate(String plate);
    TrafficTicket generateTrafficTicket(String plate);


}
