package kz.aidyninho.tipakaspi.service;

import kz.aidyninho.tipakaspi.dto.UserDto;
import kz.aidyninho.tipakaspi.model.User;
import kz.aidyninho.tipakaspi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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

        return new UserDto(user.getPhone());
    }
}
