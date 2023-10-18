package pdr.parking.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pdr.parking.dto.authenticationDto.LoginResponseTokenDto;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserLoginDto;
import pdr.parking.entities.User;
import pdr.parking.infra.security.TokenService;
import pdr.parking.service.userService.UserGateway;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserGateway userGateway;

    @PostMapping("/login")
    public LoginResponseTokenDto login(@RequestBody UserLoginDto userLoginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        return new LoginResponseTokenDto(tokenService.generateToken((User) auth.getPrincipal()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(@RequestBody UserCreateRequestDto userCreateRequestDto) throws IOException {
        userGateway.createUser(userCreateRequestDto);
    }

}
