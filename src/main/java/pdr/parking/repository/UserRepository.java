package pdr.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pdr.parking.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCpf(String cpf);

    @Query("SELECT u FROM User u JOIN u.vehicles v WHERE v.plate = ?1")
    User findUserByVehiclePlate( String plate);


}
