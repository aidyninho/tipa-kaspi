package kz.aidyninho.tipakaspi.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyPair implements Serializable {

    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyFrom;
    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyTo;
}
