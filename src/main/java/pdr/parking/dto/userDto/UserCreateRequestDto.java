package pdr.parking.dto.userDto;


import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.multipart.MultipartFile;
import pdr.parking.entities.enums.Role;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
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
