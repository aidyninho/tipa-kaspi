package kz.aidyninho.tipakaspi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyResponse {

    private String timestamp;
    private String base;
    private Map<String, String> rates;
}
