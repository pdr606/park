package pdr.parking.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.parking.service.trafficGuardService.TrafficGuardService;

@RestController
@RequestMapping(value = "/api/v1/traffic-guard")
public class TrafficGuardController {

    private TrafficGuardService trafficGuardService;

    public TrafficGuardController(TrafficGuardService trafficGuardService){
        this.trafficGuardService = trafficGuardService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{plate}")
    public void generateTrafficTicket(@PathVariable String plate){
        trafficGuardService.generateTrafficTicket(plate);
    }
}
