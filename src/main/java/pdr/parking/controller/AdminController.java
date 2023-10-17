package pdr.parking.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.vehicleDto.VehicleResponseDto;
import pdr.parking.entities.User;
import pdr.parking.mapper.VehicleMapper;
import pdr.parking.service.adminService.AdminService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAllUsers(){
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
        return VehicleMapper.toResponse(adminService.findAllVehicles());
    }

    @GetMapping(value = "/vehicles/{plate}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleResponseDto findVehicle(@PathVariable String plate){
        return VehicleMapper.toResponse(adminService.findVehicleByPlate(plate));
    }

    @PostMapping(value = "/traffic-guard/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrafficGuard(@RequestBody UserCreateRequestDto userCreateRequestDto) throws IOException {
         adminService.createTrafficGuard(userCreateRequestDto);
    }
    @PostMapping(value = "/traffic-guard/delete")
    public void deleteTrafficRider(@PathVariable Long id){
        adminService.deleteTrafficGuard(id);
    }
}
