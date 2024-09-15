package kz.aidyninho.tipakaspi.service;

import kz.aidyninho.tipakaspi.dto.AccountDto;
import kz.aidyninho.tipakaspi.dto.AccountUpdateDto;
import kz.aidyninho.tipakaspi.dto.LimitDto;
import kz.aidyninho.tipakaspi.exception.AccountNotFoundException;
import kz.aidyninho.tipakaspi.model.Account;
import kz.aidyninho.tipakaspi.model.CurrencyShortName;
import kz.aidyninho.tipakaspi.model.Limit;
import kz.aidyninho.tipakaspi.model.User;
import kz.aidyninho.tipakaspi.repository.AccountRepository;
import kz.aidyninho.tipakaspi.repository.LimitRepository;
import kz.aidyninho.tipakaspi.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class LimitService {

    private final LimitRepository limitRepository;

    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public boolean resetAllLimits() {
         return limitRepository.resetAllCurrentSum() == limitRepository.count();
    }
}
