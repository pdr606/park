package pdr.parking.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.service.adminService.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(name = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> findAll(){
        return adminService.findAllUsers();
    }
}
