package pdr.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdr.parking.entities.Park;
import pdr.parking.entities.User;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

}
