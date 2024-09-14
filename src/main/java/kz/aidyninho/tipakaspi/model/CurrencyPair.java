package kz.aidyninho.tipakaspi.model;

import jakarta.persistence.Embeddable;
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

    private String currencyFrom;
    private String currencyTo;
}
