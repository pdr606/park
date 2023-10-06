package pdr.parking.mapper;

import org.mapstruct.Mapper;
import pdr.parking.dto.UserRequestDto;
import pdr.parking.entities.User;

@Mapper
public class UserMapper {
    public static User toEntity(UserRequestDto userRequestDto){
        return new User(
                null,
                userRequestDto.firstName(),
                userRequestDto.lastName(),
                userRequestDto.cpf(),
                userRequestDto.email(),
                userRequestDto.telephone(),
                userRequestDto.password(),
                0,
                false,
                null,
                null
                );
    }
}
