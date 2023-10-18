package pdr.parking.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.parking.service.trafficGuardService.TrafficGuardService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/traffic-guards")
public class TrafficGuardController {

    private TrafficGuardService trafficGuardService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{plate}")
    public void generateTrafficTicket(@PathVariable String plate){
        trafficGuardService.generateTrafficTicket(plate);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{plate}")
    public boolean searchPlate(@PathVariable String plate){
        return trafficGuardService.checkPlate(plate);
    }

}
