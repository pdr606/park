package pdr.parking.service.userService;

import pdr.parking.dto.UserRequestDto;
import pdr.parking.model.User;

public interface UserGateway {
    void createUser(UserRequestDto userRequestDto);
    void updateUser(User user, User userUpdate);
    void addBalance(User user, Integer balance);
    void deleteUser(Long id);

}
