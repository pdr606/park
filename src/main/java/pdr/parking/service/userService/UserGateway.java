package pdr.parking.service.userService;

import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.userDto.UserUpdateRequestDto;
import pdr.parking.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserGateway {
    void createUser(UserCreateRequestDto userCreateRequestDto) throws IOException;
    void updateData(User user, UserUpdateRequestDto userUpdateRequestDto);
    void addBalance(User user, Integer balance);
    void deleteUser(Long id);
    UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);
    UserResponseDto findByVehiclePlate(String plate);
    UserResponseDto findById(Long id);
    UserResponseDto findByCpf(String cpf);
    List<UserResponseDto> findAll();
}
