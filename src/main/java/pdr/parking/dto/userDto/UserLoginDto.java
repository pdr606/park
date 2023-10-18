package pdr.parking.dto.userDto;

import javax.validation.constraints.Email;

public record UserLoginDto(@Email String email, String password) {
}
