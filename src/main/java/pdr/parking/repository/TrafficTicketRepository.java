package pdr.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdr.parking.entities.TrafficTicket;

@Repository
public interface TrafficTicketRepository extends JpaRepository<TrafficTicket, Long> {
}
