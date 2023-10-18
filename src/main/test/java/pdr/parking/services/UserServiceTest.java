package pdr.parking.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pdr.parking.dto.userDto.UserCreateRequestDto;
import pdr.parking.entities.User;
import pdr.parking.entities.enums.Role;
import pdr.parking.exceptions.UserDuplicateException;
import pdr.parking.repository.UserRepository;
import pdr.parking.service.userService.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void createUserTest() {
        // Arrange
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setFirstName("John");
        userCreateRequestDto.setLastName("Doe");
        userCreateRequestDto.setCpf("109.192.193-30");
        userCreateRequestDto.setEmail("johndoe@gmail.com");
        userCreateRequestDto.setTelephone("111111");
        userCreateRequestDto.setPassword("password");
        userCreateRequestDto.setRole(Role.USER);

        when(userRepository.existsByCpf(userCreateRequestDto.getCpf())).thenReturn(false);

        userService.createUser(userCreateRequestDto);

        verify(userRepository, times(1)).existsByCpf(userCreateRequestDto.getCpf());
        verify(userRepository, times(1)).save(any(User.class));

    }
    @Test
    void createUserWithDuplicateCpfTest() {
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setFirstName("John");
        userCreateRequestDto.setLastName("Doe");
        userCreateRequestDto.setCpf("109.192.193-30");
        userCreateRequestDto.setEmail("johndoe@gmail.com");
        userCreateRequestDto.setTelephone("111111");
        userCreateRequestDto.setPassword("password");
        userCreateRequestDto.setRole(Role.USER);

        when(userRepository.existsByCpf(userCreateRequestDto.getCpf())).thenReturn(true);

        assertThrows(UserDuplicateException.class, () -> userService.createUser(userCreateRequestDto));

        verify(userRepository, times(1)).existsByCpf(userCreateRequestDto.getCpf());
        verify(userRepository, never()).save(any(User.class));
    }
}

