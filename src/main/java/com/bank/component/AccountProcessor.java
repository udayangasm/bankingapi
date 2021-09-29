package com.bank.component;

import com.bank.bean.request.AccountRequest;
import com.bank.bean.response.AccountResponse;
import com.bank.models.Account;
import com.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountProcessor {

    @Autowired
    private AccountRepository accountRepository;

    public AccountResponse registerAccount(AccountRequest request) {

        Account account = new Account();
        account.setName(request.getName());
        account.setAccountId(request.getAccountId());
        account = accountRepository.save(account);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setName(account.getName());
        accountResponse.setAccountId(account.getAccountId());
        accountResponse.setResultCode(HttpStatus.CREATED);
        accountResponse.setResult("Account Successfully Created");

        return accountResponse;
    }

    public List<AccountResponse> getAccounts() {

        List<Account> allAccounts = accountRepository.findAll();
        List<AccountResponse>  accountResponse =new ArrayList<>();

        allAccounts.stream().forEach(account -> {
            AccountResponse response = new AccountResponse();
            response.setName(account.getName());
            response.setAccountId(account.getAccountId());
            response.setResultCode(HttpStatus.OK);
            response.setResult("Success");

            accountResponse.add(response); });

        return accountResponse;
    }

}
