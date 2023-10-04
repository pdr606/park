package pdr.parking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String cpf;
    @NotNull(message = "Email obrigátorio")
    private String email;
    private String telephone;
    private String password;
}
