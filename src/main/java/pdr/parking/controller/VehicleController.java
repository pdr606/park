package pdr.parking.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.vehicleDto.VehicleRequestDto;
import pdr.parking.service.vehicleService.VehicleService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private VehicleService vehicleService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated VehicleRequestDto vehicleRequestDto){
            vehicleService.registerVehicle(vehicleRequestDto);
    }

}
