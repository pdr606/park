package pdr.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.UserRequestDto;
import pdr.parking.entities.User;
import pdr.parking.service.userService.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated UserRequestDto userRequestDto){
            userService.createUser(userRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User search(@PathVariable Long id){
        return userService.findById(id);
    }

}
