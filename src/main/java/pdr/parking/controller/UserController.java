package pdr.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.entities.User;
import pdr.parking.service.adminService.AdminService;
import pdr.parking.service.userService.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private AdminService adminService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated UserCreateRequestDto userCreateRequestDto) throws IOException {
            userService.createUser(userCreateRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto search(@PathVariable Long id){
        return userService.findById(id);
    }

}
