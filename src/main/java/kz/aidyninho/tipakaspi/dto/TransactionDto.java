package kz.aidyninho.tipakaspi.dto;

import kz.aidyninho.tipakaspi.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    private String accountFromId;
    private String accountToId;
    private BigDecimal sum;
    private ExpenseCategory expenseCategory;
}
