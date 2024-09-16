package kz.aidyninho.tipakaspi.api;

import kz.aidyninho.tipakaspi.model.Currency;
import kz.aidyninho.tipakaspi.service.CurrencyService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PutMapping("/currencies")
    public List<Currency> updateCurrency() {
        return currencyService.updateCurrencyRates();
    }
}
