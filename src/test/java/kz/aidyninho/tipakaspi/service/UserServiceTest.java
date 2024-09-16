package kz.aidyninho.tipakaspi.service;

import kz.aidyninho.tipakaspi.dto.UserDto;
import kz.aidyninho.tipakaspi.model.User;
import kz.aidyninho.tipakaspi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    public void saveReturnsUserDtoWithTheSameUsername() {
        UserDto userDto = new UserDto("123456789");

        User user = User.builder()
                .phone("123456789")
                .build();
        Mockito.when(userRepository.save(user))
                .thenReturn(user);

        UserDto saved = userService.save(user);

        assertEquals(userDto.getPhone(), saved.getPhone());
    }

    @Test
    public void findByPhoneReturnsUserIfExists() {
        User user = User.builder()
                .phone("123456789")
                .build();
        Mockito.when(userRepository.findByPhone("123456789"))
                .thenReturn(Optional.of(user));

        User byPhone = userService.findByPhone("123456789");

        assertEquals(user, byPhone);
    }

    @Test
    public void findByPhoneThrowsIfPhoneDoesNotExist() {
        User user = User.builder()
                .phone("123456789")
                .build();
        Mockito.when(userRepository.findByPhone("123456789"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.findByPhone("123456789"));
    }

}