package pdr.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.UserRequestDto;
import pdr.parking.service.userService.UserService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;


    @PostMapping
    public Mono<Void> save(@RequestBody @Valid UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
        return Mono.empty();
    }

}
