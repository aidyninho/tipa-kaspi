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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final LimitService limitService;
    private final CurrencyService currencyService;

    public AccountService(AccountRepository accountRepository, UserService userService, LimitService limitService, CurrencyService currencyService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.limitService = limitService;
        this.currencyService = currencyService;
    }

    public Account saveAccount(AccountDto accountDto) {
        User owner = userService.findByPhone(accountDto.getUserPhone());

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

        log.info("Saving account {}", account);
        return accountRepository.save(account);
    }

    public Limit setLimit(LimitDto limitDto) {
        Account account = findById(limitDto.getAccountId());

        Limit limit = limitService.findById(account.getLimit().getId());
        limit.setLimitSum(limitDto.getLimitSum());
        limit.setLimitDatetime(OffsetDateTime.now());
        account.setLimit(limit);

        accountRepository.save(account);

        log.info("Setting limit {} to account {}", limit, account);
        return limit;
    }

    public Account topUpBalance(AccountUpdateDto accountUpdateDto) {
        Account account = findById(accountUpdateDto.getId());

        account.setBalance(account.getBalance().add(accountUpdateDto.getBalance()));

        log.info("Top up balance {} for {}", account, account.getBalance());
        return accountRepository.save(account);
    }

    public Account findById(String id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    public Account deposit(String id, BigDecimal amount) {
        Account account = findById(id);
        account.setBalance(account.getBalance().add(amount));

        log.info("Deposit {} for {}", amount, account);
        return accountRepository.save(account);
    }

    public Account withdraw(String id, BigDecimal amount) {
        Account account = findById(id);
        account.setBalance(account.getBalance().subtract(amount));
        Limit limit = limitService.findById(account.getLimit().getId());

        limit.setCurrentSum(limit.getCurrentSum()
                .add(currencyService.convertToUSD(account.getCurrencyShortName(), amount)));
        account.setLimit(limit);

        log.info("Withdraw {} for {}", amount, account);
        return accountRepository.save(account);
    }
}
