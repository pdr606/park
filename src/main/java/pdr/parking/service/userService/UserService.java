package pdr.parking.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.dto.userDto.UserResponseDto;
import pdr.parking.dto.userDto.UserUpdateRequestDto;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.exceptions.UserNotFoundException;
import pdr.parking.entities.User;
import pdr.parking.mapper.UserMapper;
import pdr.parking.repository.UserRepository;

import java.util.List;


@Service
public class UserService implements UserGateway {

    @Autowired
    UserRepository userRepository;

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
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        try{
            User user = userRepository.getReferenceById(id);
            updateData(user, userUpdateRequestDto);
            userRepository.save(user);
            return UserMapper.toResponse(user);
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
    public void addBalance(User user, Integer balance) {

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto findByVehiclePlate(String plate) {
        try{
            return UserMapper.toResponse(userRepository.findUserByVehiclePlate(plate));
        } catch (DataIntegrityViolationException ex){
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserResponseDto findById(Long id) {
        return UserMapper.toResponse(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException()
        ));
    }

    @Override
    public UserResponseDto findByCpf(String cpf) {
        try{
            return UserMapper.toResponse(userRepository.findByCpf(cpf));
        } catch (DataIntegrityViolationException ex){
            throw new UserNotFoundException();
        }
    }
    @Override
    public List<UserResponseDto> findAll() {
        return UserMapper.toResponse(userRepository.findAll());
    }
}
