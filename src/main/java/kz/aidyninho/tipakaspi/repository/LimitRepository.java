package kz.aidyninho.tipakaspi.repository;

import jakarta.transaction.Transactional;
import kz.aidyninho.tipakaspi.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LimitRepository extends JpaRepository<Limit, Integer> {

    @Query("update Limit set currentSum = 0.0")
    @Modifying
    @Transactional
    int resetAllCurrentSum();
}
