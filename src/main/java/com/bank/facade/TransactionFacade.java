package com.bank.facade;

import com.bank.bean.request.TransactionRequest;
import com.bank.bean.request.TransferRequest;
import com.bank.bean.response.AccountBalanceResponse;
import com.bank.bean.response.AccountResponse;
import com.bank.bean.response.TransactionResponse;
import com.bank.component.TransactionProcessor;
import com.bank.component.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionFacade {

    @Autowired
    private TransactionValidator transactionValidator;

    @Autowired
    private TransactionProcessor transactionProcessor;

    public TransactionResponse deposit(TransactionRequest request) {

        transactionValidator.validateDeposit(request);
        TransactionResponse transactionResponse = transactionProcessor.deposit(request);
        return transactionResponse;
    }

    public TransactionResponse withdraw(TransactionRequest request) {

        transactionValidator.validateDeposit(request);
        TransactionResponse transactionResponse = transactionProcessor.withdraw(request);
        return transactionResponse;
    }

    public TransactionResponse transfer(TransferRequest request) {

        transactionValidator.validateTransfer(request);
        TransactionResponse transactionResponse = transactionProcessor.transfer(request);
        return transactionResponse;
    }

    public AccountBalanceResponse checkBalance(String accountId) {

        AccountBalanceResponse accountBalanceResponse = transactionProcessor.checkBalance(accountId);
        return accountBalanceResponse;
    }


}
