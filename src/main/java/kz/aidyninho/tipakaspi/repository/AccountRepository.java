package kz.aidyninho.tipakaspi.repository;

import kz.aidyninho.tipakaspi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
