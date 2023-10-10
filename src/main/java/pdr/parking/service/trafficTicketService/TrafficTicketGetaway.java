package pdr.parking.service.trafficTicketService;

import pdr.parking.entities.TrafficTicket;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;

public interface TrafficTicketGetaway {

    TrafficTicket generateTrafficTicket(User user, Vehicle vehicle);
}
