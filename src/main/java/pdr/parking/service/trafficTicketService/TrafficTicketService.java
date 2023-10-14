package pdr.parking.service.trafficTicketService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdr.parking.entities.TrafficTicket;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.repository.TrafficTicketRepository;

import java.time.LocalDateTime;

@Service
public class TrafficTicketService implements TrafficTicketGetaway {


    private final TrafficTicketRepository trafficTicketRepository;

    public TrafficTicketService(TrafficTicketRepository trafficTicketRepository) {
        this.trafficTicketRepository = trafficTicketRepository;
    }

    @Override
    public TrafficTicket generateTrafficTicket(User user, Vehicle vehicle){
        return trafficTicketRepository.save(new TrafficTicket(
                user,
                vehicle)
        );
    }
}
