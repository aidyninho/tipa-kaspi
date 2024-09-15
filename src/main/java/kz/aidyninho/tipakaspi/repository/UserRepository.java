package kz.aidyninho.tipakaspi.repository;

import kz.aidyninho.tipakaspi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByPhone(String phone);
}
