package pdr.parking.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    final TokenService tokenService;
    final AuthenticationManager authenticationManager;
    final UserGateway userGateway;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseTokenDto> login(@RequestBody UserLoginDto userLoginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseTokenDto(token));
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserCreateRequestDto userCreateRequestDto) throws IOException {
        if(userGateway.findByEmail(userCreateRequestDto.getEmail()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(userCreateRequestDto.getPassword());
        userCreateRequestDto.setPassword(encryptedPassword);
        userGateway.createUser(userCreateRequestDto);
        return  ResponseEntity.ok().build();
    }

}
