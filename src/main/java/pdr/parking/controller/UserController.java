package pdr.parking.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.entities.User;
import pdr.parking.mapper.UserDtoMapper;
import pdr.parking.service.adminService.AdminService;
import pdr.parking.service.userService.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final  UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto search(@PathVariable Long id){
        return UserDtoMapper.toResponse(userService.findById(id));
    }

}
