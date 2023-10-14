package pdr.parking.service.parkService;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pdr.parking.dto.parkDto.ParkingRequestDto;
import pdr.parking.entities.Park;

import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.mapper.UserMapper;
import pdr.parking.repository.ParkRepository;
import pdr.parking.service.trafficTicketService.TrafficTicketGetaway;

import pdr.parking.service.userService.UserGateway;

import pdr.parking.service.vehicleService.VehicleGateway;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@Service
public class ParkService implements ParkGateway {

    private final ParkRepository parkRepository;
    private final UserGateway userGateway;
    private final VehicleGateway vehicleGateway;
    private final TrafficTicketGetaway trafficTicketGetaway;

    public ParkService(ParkRepository parkRepository, UserGateway userGateway, VehicleGateway vehicleGateway, TrafficTicketGetaway trafficTicketGetaway) {
        this.parkRepository = parkRepository;
        this.userGateway = userGateway;
        this.vehicleGateway = vehicleGateway;
        this.trafficTicketGetaway = trafficTicketGetaway;
    }

    @Override
    public Park generatePark(ParkingRequestDto parkingRequestDto) {
        User user = UserMapper.toEntity(userGateway.findById(parkingRequestDto.userId()));
        Vehicle vehicle = vehicleGateway.findById(parkingRequestDto.vehicleId());
        return parkRepository.save(new Park(parkingRequestDto.totalTime(),
                user,
                vehicle));
    }

    @Override
    @Cacheable(cacheNames = "parks")
    @Scheduled(fixedRate = 5000)
    public void checkExpiration() {
        List<Park> parkList = parkRepository.filterExpiredPark();
        for (Park park : parkList){
            if(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
                    .isAfter(park.getExpirationAt())){
                        park.setCurrent(false);
            }
        }
    }

    @Override
    public boolean checkPlate(String plate) {
        boolean exist = parkRepository.existsByVehiclePlate(plate);
        if(!exist){
            User user = UserMapper.toEntity(userGateway.findByVehiclePlate(plate));
            Optional<Vehicle> optionalVehicle =
                    user.getVehicles().stream().filter(
                            v -> v.getPlate().equals(plate)).findFirst();

            if (optionalVehicle.isPresent()) {
                Vehicle vehicle = optionalVehicle.get();
                trafficTicketGetaway.generateTrafficTicket(user, vehicle);
            }
        }
        return exist;
    }

    @Override
    public List<Park> findAll() {
        return parkRepository.findAll();
    }
}
