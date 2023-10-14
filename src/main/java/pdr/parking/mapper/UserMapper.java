package pdr.parking.mapper;

import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<UserResponseDto> toResponse(List<User> users){
        return
                users.stream().map(user ->
                                new UserResponseDto(
                                        user.getFirstName(),
                                        user.getLastName(),
                                        user.getCpf(),
                                        user.getTelephone(),
                                        user.getEmail(),
                                        user.getId(),
                                        user.getBalance(),
                                        user.getRole(),
                                        user.getVehicles(),
                                        user.getParks(),
                                        user.getTrafficTickets()
                                )).collect(Collectors.toList());

    }

    public static UserResponseDto toResponse(User user){
        return new UserResponseDto(
                user.getFirstName(),
                user.getLastName(),
                user.getCpf(),
                user.getTelephone(),
                user.getEmail(),
                user.getId(),
                user.getBalance(),
                user.getRole(),
                user.getVehicles(),
                user.getParks(),
                user.getTrafficTickets())
                ;
    }

    public static User toEntity(UserResponseDto userResponseDto){
        return new User(
                userResponseDto.firstName(),
                userResponseDto.lastName(),
                userResponseDto.cpf(),
                userResponseDto.email(),
                userResponseDto.telephone(),
                null,
                null);
    }
}
