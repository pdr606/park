package pdr.parking.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserUpdateRequestDto;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.exceptions.UserNotFoundException;
import pdr.parking.entities.User;
import pdr.parking.repository.UserRepository;


@Service
public class UserService implements UserGateway {

    @Autowired
    UserRepository userRepository;

    public void createUser(UserCreateRequestDto userCreateRequestDto) {
        if(!userRepository.existsByCpf(userCreateRequestDto.cpf())){
            userRepository.save(new User(userCreateRequestDto.firstName(),
                    userCreateRequestDto.lastName(),
                    userCreateRequestDto.telephone(),
                    userCreateRequestDto.cpf(),
                    userCreateRequestDto.email(),
                    userCreateRequestDto.password()));
            return;
        }
        throw new UserDuplicateException(userCreateRequestDto.cpf());
    }

    @Override
    public void updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = findById(id);
        user.setEmail(userUpdateRequestDto.email());
        user.setTelephone(userUpdateRequestDto.telephone());
        userRepository.save(user);
    }

    @Override
    public void addBalance(User user, Integer balance) {

    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findByVehiclePlate(String plate) {
        try{
            return userRepository.findUserByVehiclePlate(plate);
        } catch (DataIntegrityViolationException ex){
            throw new UserNotFoundException();
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
            new UserNotFoundException()
                );
    }
}
