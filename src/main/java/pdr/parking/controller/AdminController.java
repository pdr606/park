package pdr.parking.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.User;
import pdr.parking.service.adminService.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> findAllUsers(){
        return adminService.findAllUsers();
    }

    @GetMapping(value = "/users/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public User findUser(@PathVariable String cpf){
        return adminService.findUserByCpf(cpf);
    }

    @GetMapping(value = "/vehicles")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponseDto> findAllVehicles(){
        return adminService.findAllVehicles();
    }

    @GetMapping(value = "/vehicles/{plate}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponseDto findVehicle(@PathVariable String plate){
        return adminService.findVehicleByPlate(plate);
    }

    @PostMapping(value = "/traffic-guard/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrafficRider(@RequestBody UserCreateRequestDto userCreateRequestDto){
         adminService.createTrafficGuard(userCreateRequestDto);
    }
}
