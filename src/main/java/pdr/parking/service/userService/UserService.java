package pdr.parking.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdr.parking.dto.UserRequestDto;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.exceptions.UserNotFoundException;
import pdr.parking.mapper.UserMapper;
import pdr.parking.entities.User;
import pdr.parking.repository.UserRepository;


@Service
public class UserService implements UserGateway {

    @Autowired
    UserRepository userRepository;

    public void createUser(UserRequestDto userRequestDto) {
        if(!userRepository.existsByCpf(userRequestDto.cpf())){
            userRepository.save(UserMapper.toEntity(userRequestDto));
            return;
        }
        throw new UserDuplicateException(userRequestDto.cpf());
    }

    @Override
    public void updateUser(User user, User userUpdate) {

    }

    @Override
    public void addBalance(User user, Integer balance) {

    }

    @Override
    public void deleteUser(Long id) {

    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
            new UserNotFoundException()
                );
    }
}
