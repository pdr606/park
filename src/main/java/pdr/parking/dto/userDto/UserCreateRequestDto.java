package pdr.parking.dto.userDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import pdr.parking.entities.enums.Role;
@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequestDto
{
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String telephone;
    private String password;
    private Role role;
    private MultipartFile image;
}
