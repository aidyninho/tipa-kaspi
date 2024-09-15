package kz.aidyninho.tipakaspi.dto;

import kz.aidyninho.tipakaspi.model.CurrencyShortName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private String id;
    private CurrencyShortName currencyShortName;
    private String userPhone;
}
