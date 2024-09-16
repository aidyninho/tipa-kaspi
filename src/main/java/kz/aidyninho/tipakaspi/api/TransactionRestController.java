package kz.aidyninho.tipakaspi.api;

import kz.aidyninho.tipakaspi.dto.TransactionDto;
import kz.aidyninho.tipakaspi.dto.TransactionReadDto;
import kz.aidyninho.tipakaspi.model.Transaction;
import kz.aidyninho.tipakaspi.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionRestController {

    private final TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public Transaction makeTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.makeTransaction(transactionDto);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactionsByAccountAndStatus(@RequestBody TransactionReadDto transactionDto) {
        return transactionService.findAllTransactionsByAccountIdAndStatus(transactionDto);
    }
}
