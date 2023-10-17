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
    void addBalance(Long userId, Integer balance);
    void deleteUser(Long id);
    User updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);
    User findByVehiclePlate(String plate);
    User findById(Long id);
    User findByCpf(String cpf);
    List<User> findAll();
}
