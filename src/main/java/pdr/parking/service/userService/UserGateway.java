package pdr.parking.service.userService;

import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserUpdateRequestDto;
import pdr.parking.entities.User;

public interface UserGateway {
    void createUser(UserCreateRequestDto userCreateRequestDto);
    void updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto);
    void addBalance(User user, Integer balance);
    void deleteUser(Long id);


    User findByVehiclePlate(String plate);
    User findById(Long id);

}
