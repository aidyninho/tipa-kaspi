package kz.aidyninho.tipakaspi.repository;

import kz.aidyninho.tipakaspi.model.Currency;
import kz.aidyninho.tipakaspi.model.CurrencyPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, CurrencyPair> {

}
