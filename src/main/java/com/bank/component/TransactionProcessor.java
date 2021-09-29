package com.bank.component;

import com.bank.bean.request.TransactionRequest;
import com.bank.bean.request.TransferRequest;
import com.bank.bean.response.AccountBalanceResponse;
import com.bank.bean.response.AccountResponse;
import com.bank.bean.response.TransactionResponse;
import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionProcessor {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Transactional
    public TransactionResponse deposit(TransactionRequest request) {

        Transaction transaction = new Transaction();
        transaction.setAccountId(request.getAccountId());
        transaction.setAmount(request.getAmount());
        LocalDateTime time = LocalDateTime.now();
        transaction.setTransactionTime(time);

        transaction = transactionRepository.save(transaction);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setResult("Deposit is successful");
        transactionResponse.setResultCode(HttpStatus.CREATED);

        return transactionResponse;
    }


    @Transactional
    public TransactionResponse withdraw(TransactionRequest request) {

        Transaction transaction = new Transaction();
        transaction.setAccountId(request.getAccountId());

        double amount = request.getAmount() * -1;
        transaction.setAmount(amount);
        LocalDateTime time = LocalDateTime.now();
        transaction.setTransactionTime(time);

        transaction = transactionRepository.save(transaction);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setResult("Withdraw is successful");
        transactionResponse.setResultCode(HttpStatus.CREATED);

        return transactionResponse;
    }

    @Transactional
    public TransactionResponse transfer(TransferRequest request) {

        Transaction withdraw = new Transaction();
        withdraw.setAccountId(request.getSenderAccountId());
        withdraw.setAmount(request.getAmount() * -1);
        LocalDateTime time = LocalDateTime.now();
        withdraw.setTransactionTime(time);
        withdraw = transactionRepository.save(withdraw);


        Transaction deposit = new Transaction();
        deposit.setAccountId(request.getRecipientAccountId());
        withdraw.setAmount(request.getAmount() );
        withdraw.setTransactionTime(time);
        withdraw = transactionRepository.save(withdraw);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setResult("Transfer is successful");
        transactionResponse.setResultCode(HttpStatus.OK);

        return transactionResponse;
    }


    public AccountBalanceResponse checkBalance(String accountId) {

        AccountBalanceResponse response = new AccountBalanceResponse();

        List<Transaction> allUserTransaction = transactionRepository.findByAccountId(accountId);
        double totalAmount = allUserTransaction.stream().filter(c -> c.getAmount() != null).mapToDouble(Transaction::getAmount).sum();
        response.setAccountBalance(totalAmount);
        response.setAccountId(accountId);
        response.setResult("SUCCESS");
        response.setResultCode(HttpStatus.OK);
        return response;

    }
}
