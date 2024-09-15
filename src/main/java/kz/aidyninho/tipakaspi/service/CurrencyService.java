package kz.aidyninho.tipakaspi.service;

import jakarta.transaction.Transactional;
import kz.aidyninho.tipakaspi.exception.FailedToGetCurrencyFromSourceException;
import kz.aidyninho.tipakaspi.model.Currency;
import kz.aidyninho.tipakaspi.model.CurrencyPair;
import kz.aidyninho.tipakaspi.model.CurrencyResponse;
import kz.aidyninho.tipakaspi.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final WebClient webClient;

    @Value("${app.currency-rate.source}")
    private String url;

    public CurrencyService(CurrencyRepository currencyRepository, WebClient webClient) {
        this.currencyRepository = currencyRepository;
        this.webClient = webClient;
    }

    @Transactional
    public List<Currency> updateCurrencyRates() {
        try {
            webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(CurrencyResponse.class)
                    .doOnSuccess(response -> currencyRepository.saveAll(getCurrencyFromResponse(response)))
                    .block();
        } catch (RuntimeException e) {
            throw new FailedToGetCurrencyFromSourceException("Failed to get currency from source " + url, e);
        }

        return currencyRepository.findAll();
    }

    private List<Currency> getCurrencyFromResponse(CurrencyResponse currencyResponse) {
        List<Currency> currencies = new ArrayList<>();
        String base = currencyResponse.getBase();

        for (String currency : currencyResponse.getRates().keySet()) {
            CurrencyPair currencyPair = new CurrencyPair(base, currency);
            Instant instant = Instant.ofEpochSecond(Long.parseLong(currencyResponse.getTimestamp()));

            Currency currency1 = Currency.builder()
                    .currencyPair(currencyPair)
                    .rate(new BigDecimal(currencyResponse.getRates().get(currency)))
                    .datetime(OffsetDateTime.ofInstant(instant, ZoneOffset.UTC))
                    .build();

            currencies.add(currency1);
        }

        return currencies;
    }
}
