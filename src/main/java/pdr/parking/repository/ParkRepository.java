package pdr.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdr.parking.entities.Park;
import pdr.parking.entities.User;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

    @Query(value = "SELECT * FROM park WHERE expirationAt <= CURRENT_TIMESTAMP", nativeQuery = true)
    List<Park> filterExpiredPark();


}
