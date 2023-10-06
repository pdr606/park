package pdr.parking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.ParkingRequestDto;
import pdr.parking.entities.Park;
import pdr.parking.service.parkService.ParkService;

@RestController
@RequestMapping("/api/v1/park")
public class ParkController {

    @Autowired
    private ParkService parkService;

    public ParkController(ParkService parkService) {
        this.parkService = parkService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Park create (@RequestBody ParkingRequestDto parkingRequestDto){
        return parkService.generatePark(parkingRequestDto);
    }
}
