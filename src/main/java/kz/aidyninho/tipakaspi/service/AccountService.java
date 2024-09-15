package kz.aidyninho.tipakaspi.service;

import kz.aidyninho.tipakaspi.dto.AccountDto;
import kz.aidyninho.tipakaspi.dto.AccountUpdateDto;
import kz.aidyninho.tipakaspi.exception.AccountNotFoundException;
import kz.aidyninho.tipakaspi.model.Account;
import kz.aidyninho.tipakaspi.model.User;
import kz.aidyninho.tipakaspi.repository.AccountRepository;
import kz.aidyninho.tipakaspi.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account saveAccount(AccountDto accountDto) {
        User owner = userRepository.findByPhone(accountDto.getUserPhone())
                .orElseThrow(() -> new UsernameNotFoundException(accountDto.getUserPhone()));

        Account account = Account.builder()
                .id(accountDto.getId())
                .balance(BigDecimal.ZERO)
                .currencyShortName(accountDto.getCurrencyShortName())
                .user(owner)
                .build();

        return accountRepository.save(account);
    }

    public Account update(AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getId())
                .orElseThrow(AccountNotFoundException::new);

        account.setBalance(accountUpdateDto.getBalance());

        return accountRepository.save(account);
    }

    public Account topUpBalance(AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getId())
                .orElseThrow(AccountNotFoundException::new);

        account.setBalance(account.getBalance().add(accountUpdateDto.getBalance()));

        return accountRepository.save(account);
    }
}
