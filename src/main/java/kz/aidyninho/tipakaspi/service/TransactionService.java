package kz.aidyninho.tipakaspi.service;

import jakarta.transaction.Transactional;
import kz.aidyninho.tipakaspi.dto.TransactionDto;
import kz.aidyninho.tipakaspi.dto.TransactionReadDto;
import kz.aidyninho.tipakaspi.model.Account;
import kz.aidyninho.tipakaspi.model.Transaction;
import kz.aidyninho.tipakaspi.model.TransactionStatus;
import kz.aidyninho.tipakaspi.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CurrencyService currencyService;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, CurrencyService currencyService, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.currencyService = currencyService;
        this.accountService = accountService;
    }

    @Transactional
    public Transaction makeTransaction(TransactionDto transactionDto) {
        Account accountFrom = accountService.findById(transactionDto.getAccountFromId());
        Account accountTo = accountService.findById(transactionDto.getAccountToId());
        Transaction transaction = Transaction.builder()
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .sum(transactionDto.getSum())
                .expenseCategory(transactionDto.getExpenseCategory())
                .datetime(OffsetDateTime.now())
                .build();
        BigDecimal accountFromBalanceUSD = currencyService.convertToUSD(accountFrom.getCurrencyShortName(), accountFrom.getBalance());

        if (accountFromBalanceUSD.compareTo(transactionDto.getSum()) < 0) {
            transaction.setStatus(TransactionStatus.NOT_ENOUGH_BALANCE);

            Transaction savedTransaction = transactionRepository.save(transaction);
            log.info("Not enough balance for making transaction {}", savedTransaction.getId());
            return savedTransaction;
        }

        if (accountFrom.getLimit().getCurrentSum().add(transactionDto.getSum())
                .compareTo(accountFrom.getLimit().getLimitSum()) > 0) {
            transaction.setStatus(TransactionStatus.LIMIT_EXCEEDED);

            Transaction savedTransaction = transactionRepository.save(transaction);
            log.info("Limit exceeded for making transaction {}", savedTransaction.getId());
            return savedTransaction;
        }

        accountService.withdraw(
                accountFrom.getId(),
                currencyService.convertFromUSD(accountFrom.getCurrencyShortName(), transactionDto.getSum())
        );

        accountService.deposit(
                accountTo.getId(),
                currencyService.convertFromUSD(accountTo.getCurrencyShortName(), transactionDto.getSum())
        );

        transaction.setStatus(TransactionStatus.SUCCESS);

        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction {} successfully made", savedTransaction.getId());
        return savedTransaction;
    }

    public List<Transaction> findAllTransactionsByAccountIdAndStatus(TransactionReadDto transactionReadDto) {
        Account account = accountService.findById(transactionReadDto.getAccountId());

        log.info("Finding transactions by accountId {}", account.getId());
        return transactionRepository.findAllByAccountFromAndStatus(
                account,
                transactionReadDto.getTransactionStatus()
        );
    }
}
