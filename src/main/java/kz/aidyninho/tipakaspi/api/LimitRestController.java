package kz.aidyninho.tipakaspi.api;

import kz.aidyninho.tipakaspi.dto.LimitDto;
import kz.aidyninho.tipakaspi.model.Limit;
import kz.aidyninho.tipakaspi.service.AccountService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LimitRestController {

    private final AccountService accountService;

    public LimitRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/limits")
    public Limit setLimit(@RequestBody LimitDto limitDto) {
        return accountService.setLimit(limitDto);
    }
}
