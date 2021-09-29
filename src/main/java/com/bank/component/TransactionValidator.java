package com.bank.component;

import com.bank.bean.request.TransactionRequest;
import com.bank.bean.request.TransferRequest;
import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class TransactionValidator {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void validateDeposit(TransactionRequest request){

        if (ObjectUtils.isEmpty(request.getAccountId())){
            throw new ValidationException("Error: Account Id is empty");
        }else if (request.getAmount() <= 0){
            throw new ValidationException("Error: Deposit amount should be grater than 0");
        }else {
            if(!accountRepository.existsByAccountId(request.getAccountId())){
                throw new ValidationException("Error: Account ID does not exists");
            }
        }
    }

    public void validateWithdraw(TransactionRequest request){

        if (ObjectUtils.isEmpty(request.getAccountId())){
            throw new ValidationException("Error: Account Id is empty");
        }else if (request.getAmount() <= 0){
            throw new ValidationException("Error: Withdraw amount should be grater than 0");
        }else {
            if(!accountRepository.existsByAccountId(request.getAccountId())){
                throw new ValidationException("Error: Account ID does not exists");
            }else{
                List<Transaction> accountTransactions = transactionRepository.findByAccountId(request.getAccountId());
                double accountBalance = accountTransactions.stream().mapToDouble(e -> e.getAmount()).sum();
                if (accountBalance < request.getAmount()){
                    throw new ValidationException("Error: Withdrawal amount is grater than account balance");
                }
            }
        }
    }

    public void validateTransfer(TransferRequest request){

        if (ObjectUtils.isEmpty(request.getSenderAccountId())){

            throw new ValidationException("Error: Sender Account Id should not be empty");
        }

        if (ObjectUtils.isEmpty(request.getRecipientAccountId())){

            throw new ValidationException( "Error: Recipient Account Id should not be empty");
        }

        if (request.getAmount() == 0){

            throw new ValidationException("Error: Account should be grater than 0");
        }
    }


}
