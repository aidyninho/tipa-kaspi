package kz.aidyninho.tipakaspi.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "currencies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Currency {

    @EmbeddedId
    private CurrencyPair currencyPair;
    private BigDecimal rate;
    private OffsetDateTime datetime;
}
