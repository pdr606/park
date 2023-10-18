package pdr.parking.service.userService;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserUpdateRequestDto;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.exceptions.UserNotFoundException;
import pdr.parking.entities.User;
import pdr.parking.repository.UserRepository;
import pdr.parking.service.authorizationService.AuthorizationService;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserCreateRequestDto userCreateRequestDto) {
        findByEmail(userCreateRequestDto.getEmail());
        if(!userRepository.existsByCpf(userCreateRequestDto.getCpf())){
            userCreateRequestDto.setPassword(AuthorizationService.encryptedPassword(
                    userCreateRequestDto.getPassword()
            ));
            userRepository.save(new User(
                    userCreateRequestDto.getFirstName(),
                    userCreateRequestDto.getLastName(),
                    userCreateRequestDto.getCpf(),
                    userCreateRequestDto.getEmail(),
                    userCreateRequestDto.getTelephone(),
                    userCreateRequestDto.getPassword(),
                    userCreateRequestDto.getRole()));
            return;
        }
        throw new UserDuplicateException(userCreateRequestDto.getCpf());
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
    public UserDetails findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (DataIntegrityViolationException ex){
            throw new UserNotFoundException();
        }
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
