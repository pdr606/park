package pdr.parking.dto.userDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import pdr.parking.entities.enums.Role;

public record UserCreateRequestDto(String firstName,
                                   String lastName,
                                   String cpf,
                                   String email,
                                   String telephone,
                                   String password,
                                   Role role
)
{
}
