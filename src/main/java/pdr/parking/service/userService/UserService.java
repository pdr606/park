package pdr.parking.service.userService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserUpdateRequestDto;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.exceptions.UserNotFoundException;
import pdr.parking.entities.User;
import pdr.parking.repository.UserRepository;

import java.util.List;


@Service
public class UserService implements UserGateway {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void createUser(UserCreateRequestDto userCreateRequestDto) {
        if(!userRepository.existsByCpf(userCreateRequestDto.cpf())){
            userRepository.save(new User(
                    userCreateRequestDto.firstName(),
                    userCreateRequestDto.lastName(),
                    userCreateRequestDto.cpf(),
                    userCreateRequestDto.email(),
                    userCreateRequestDto.telephone(),
                    userCreateRequestDto.password(),
                    userCreateRequestDto.role()));
            return;
        }
        throw new UserDuplicateException(userCreateRequestDto.cpf());
    }

    @Override
    public User updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        try{
            User user = userRepository.getReferenceById(id);
            updateData(user, userUpdateRequestDto);
            userRepository.save(user);
            return user;
        } catch (DataIntegrityViolationException ex){
            throw new UserNotFoundException();
        }
    }

    @Override
    public void updateData(User user, UserUpdateRequestDto userUpdateRequestDto) {
        user.setEmail(userUpdateRequestDto.email());
        user.setTelephone(userUpdateRequestDto.telephone());
        userRepository.save(user);
    }

    @Override
    public void addBalance(Long userId, Integer balance) {
        User user = findById(userId);
        user.setBalance(balance);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
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
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByCpf(String cpf) {
        try{
            return userRepository.findByCpf(cpf);
        } catch (DataIntegrityViolationException ex){
            throw new UserNotFoundException();
        }
    }
    @Override
    public List<User> findAll() {
        return (userRepository.findAll());
    }
}
