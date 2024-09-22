package kz.aidyninho.tipakaspi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyShortName;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "user_phone")
    @ToString.Exclude
    @JsonIgnore
    private User user;
    @OneToOne
    @JoinColumn(name = "limit_id")
    @JsonIgnore
    private Limit limit;
}
