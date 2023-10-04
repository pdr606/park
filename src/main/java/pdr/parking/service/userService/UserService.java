package pdr.parking.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdr.parking.dto.UserRequestDto;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.mapper.UserMapper;
import pdr.parking.model.User;
import pdr.parking.repository.UserRepository;


@Service
public class UserService implements UserGateway {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public void createUser(UserRequestDto userRequestDto) {
        if(!userRepository.existsByCpf(userRequestDto.getCpf())){
            userRepository.save(userMapper.toEntity(userRequestDto));
            return;
        }
        throw new UserDuplicateException(userRequestDto.getCpf());
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
}
