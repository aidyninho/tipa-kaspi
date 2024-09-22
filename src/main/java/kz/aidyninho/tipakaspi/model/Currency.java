package kz.aidyninho.tipakaspi.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "currencies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency {

    @EmbeddedId
    private CurrencyPair currencyPair;
    private BigDecimal rate;
    private OffsetDateTime datetime;
}
