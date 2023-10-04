package pdr.parking.mapper;

import org.mapstruct.Mapper;
import pdr.parking.dto.UserRequestDto;
import pdr.parking.model.User;

@Mapper
public class UserMapper {
    public User toEntity(UserRequestDto userRequestDto){
        return new User(
                null,
                userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                userRequestDto.getCpf(),
                userRequestDto.getEmail(),
                userRequestDto.getTelephone(),
                userRequestDto.getPassword(),
                0,
                false
                );
    }
}
