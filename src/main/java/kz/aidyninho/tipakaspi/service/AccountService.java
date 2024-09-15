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

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final LimitRepository limitRepository;
    private final CurrencyService currencyService;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, LimitRepository limitRepository, CurrencyService currencyService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.limitRepository = limitRepository;
        this.currencyService = currencyService;
    }

    public Account saveAccount(AccountDto accountDto) {
        User owner = userRepository.findByPhone(accountDto.getUserPhone())
                .orElseThrow(() -> new UsernameNotFoundException(accountDto.getUserPhone()));

        Limit limit = Limit.builder()
                .currencyShortName(CurrencyShortName.USD)
                .limitDatetime(OffsetDateTime.now())
                .limitSum(new BigDecimal("1000"))
                .currentSum(BigDecimal.ZERO)
                .build();

        Account account = Account.builder()
                .id(accountDto.getId())
                .balance(BigDecimal.ZERO)
                .currencyShortName(accountDto.getCurrencyShortName())
                .user(owner)
                .limit(limit)
                .build();

        limitRepository.save(limit);
        return accountRepository.save(account);
    }

    public Limit setLimit(LimitDto limitDto) {
        Account account = accountRepository.findById(limitDto.getAccountId())
                .orElseThrow(AccountNotFoundException::new);

        Limit limit = limitRepository.findById(account.getLimit().getId())
                .orElseThrow(AccountNotFoundException::new);

        limit.setLimitSum(limitDto.getLimitSum());
        limit.setLimitDatetime(OffsetDateTime.now());
        account.setLimit(limit);

        accountRepository.save(account);

        return limit;
    }

    public Account topUpBalance(AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getId())
                .orElseThrow(AccountNotFoundException::new);

        account.setBalance(account.getBalance().add(accountUpdateDto.getBalance()));

        return accountRepository.save(account);
    }

    public Account findById(String id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    public Account deposit(String id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    public Account withdraw(String id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        account.setBalance(account.getBalance().subtract(amount));
        Limit limit = limitRepository.findById(account.getLimit().getId())
                .orElseThrow(AccountNotFoundException::new);

        limit.setCurrentSum(limit.getCurrentSum()
                .add(currencyService.convertToUSD(account.getCurrencyShortName(), amount)));
        account.setLimit(limit);
        return accountRepository.save(account);
    }
}
