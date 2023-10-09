package pdr.parking.service.parkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pdr.parking.dto.parkDto.ParkingRequestDto;
import pdr.parking.entities.Park;
import pdr.parking.entities.User;
import pdr.parking.entities.Vehicle;
import pdr.parking.repository.ParkRepository;
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

    public ParkService() {
    }

    public ParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public ParkService(ParkRepository parkRepository,
                       UserService userService,
                       VehicleService vehicleService) {
        this.parkRepository = parkRepository;
        this.userService = userService;
        this.vehicleService = vehicleService;
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
    @Scheduled(fixedRate = 5000)
    public void checkExpiration() {
        List<Park> parkList = parkRepository.filterExpiredPark();

        for (Park park : parkList){
            if(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).isAfter(park.getExpirationAt())){
                parkRepository.delete(park);
            }
        }
    }
}
