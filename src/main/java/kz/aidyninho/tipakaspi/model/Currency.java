package kz.aidyninho.tipakaspi.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "currencies")
public class Currency {

    @EmbeddedId
    private CurrencyPair currencyPair;
    private BigDecimal rate;
    private OffsetDateTime datetime;
}
