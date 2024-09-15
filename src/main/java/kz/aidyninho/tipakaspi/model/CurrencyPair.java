package kz.aidyninho.tipakaspi.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyPair implements Serializable {

    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyFrom;
    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyTo;
}
