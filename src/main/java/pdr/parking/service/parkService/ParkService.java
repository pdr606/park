package pdr.parking.service.parkService;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pdr.parking.dto.parkDto.ParkingRequestDto;
import pdr.parking.entities.Park;
import pdr.parking.entities.TrafficTicket;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.repository.ParkRepository;
import pdr.parking.service.trafficTicketService.TrafficTicketService;
import pdr.parking.service.userService.UserService;
import pdr.parking.service.vehicleService.VehicleService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ParkService implements ParkGateway {

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TrafficTicketService trafficTicketService;

    public ParkService() {
    }

    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public ParkService(ParkRepository parkRepository,
                       UserService userService,
                       VehicleService vehicleService,
                       TrafficTicketService trafficTicketService) {
        this.parkRepository = parkRepository;
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.trafficTicketService = trafficTicketService;
    }

    @Override
    public Park generatePark(ParkingRequestDto parkingRequestDto) {
        User user = userService.findById(parkingRequestDto.userId());
        Vehicle vehicle = vehicleService.findById(parkingRequestDto.vehicleId());
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
            if(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).isAfter(park.getExpirationAt())){
                trafficTicketService.generateTrafficTicket(park.getUser(), park.getVehicle());
                parkRepository.delete(park);
            }
        }
    }
}
