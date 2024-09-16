package kz.aidyninho.tipakaspi.repository;

import kz.aidyninho.tipakaspi.model.Account;
import kz.aidyninho.tipakaspi.model.Transaction;
import kz.aidyninho.tipakaspi.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccountFromAndStatus(Account accountFrom, TransactionStatus status);
}
