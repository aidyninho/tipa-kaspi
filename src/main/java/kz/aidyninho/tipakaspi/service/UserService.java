package kz.aidyninho.tipakaspi.service;

import kz.aidyninho.tipakaspi.dto.UserDto;
import kz.aidyninho.tipakaspi.model.User;
import kz.aidyninho.tipakaspi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user {}", username);
        return userRepository.findByPhone(username).map(
                user -> new org.springframework.security.core.userdetails.User(
                        user.getPhone(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )
        ).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user " + username));
    }

    public UserDto save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        log.info("Saved user {}", user.getPhone());
        return new UserDto(user.getPhone());
    }

    public User findByPhone(String phone) {
        log.info("Finding user by phone {}", phone);
        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to find user " + phone));
    }
}
