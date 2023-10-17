package pdr.parking.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.parkDto.ParkingPlateRequestDto;
import pdr.parking.dto.parkDto.ParkingRequestDto;
import pdr.parking.entities.Park;
import pdr.parking.service.parkService.ParkService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/park")
public class  ParkController {

    private ParkService parkService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Park create (@RequestBody ParkingRequestDto parkingRequestDto){
        return parkService.generatePark(parkingRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public boolean verifyPlate(@RequestBody ParkingPlateRequestDto parkingPlateRequestDto){
        return parkService.checkPlate(parkingPlateRequestDto.plate());
    }
}
