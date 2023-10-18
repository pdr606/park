package pdr.parking.dto.userDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.multipart.MultipartFile;
import pdr.parking.entities.enums.Role;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequestDto
{
    private String firstName;
    private String lastName;
    @CPF
    private String cpf;
    @Email
    private String email;
    private String telephone;
    private String password;
    private Role role;
}
