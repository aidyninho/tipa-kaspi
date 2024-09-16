package kz.aidyninho.tipakaspi.dto;

import kz.aidyninho.tipakaspi.model.TransactionStatus;
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
public class TransactionReadDto {

    private String accountId;
    private TransactionStatus transactionStatus;
}
