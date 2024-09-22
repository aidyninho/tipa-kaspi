package kz.aidyninho.tipakaspi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "limits")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal limitSum;
    private BigDecimal currentSum;
    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyShortName;
    private OffsetDateTime limitDatetime;
    @OneToOne(mappedBy = "limit")
    @ToString.Exclude
    @JsonIgnore
    private Account account;
}
