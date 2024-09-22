package kz.aidyninho.tipakaspi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse {

    private String timestamp;
    private String base;
    private Map<String, String> rates;
}
