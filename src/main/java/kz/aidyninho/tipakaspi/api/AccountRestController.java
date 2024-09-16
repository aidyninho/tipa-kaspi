package kz.aidyninho.tipakaspi.api;

import kz.aidyninho.tipakaspi.dto.AccountDto;
import kz.aidyninho.tipakaspi.dto.AccountUpdateDto;
import kz.aidyninho.tipakaspi.model.Account;
import kz.aidyninho.tipakaspi.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public Account save(@RequestBody AccountDto account) {
        return accountService.saveAccount(account);
    }

    @PutMapping("/accounts")
    public Account topUpBalance(@RequestBody AccountUpdateDto account) {
        return accountService.topUpBalance(account);
    }
}
