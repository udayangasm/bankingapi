package com.bank.facade;

import com.bank.bean.request.AccountRequest;
import com.bank.bean.response.AccountResponse;
import com.bank.component.AccountProcessor;
import com.bank.component.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AccountFacade {

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountProcessor accountProcessor;

    public AccountResponse registerAccount(AccountRequest request) {

        accountValidator.validateRegisterAccount(request);
        AccountResponse accountResponse = accountProcessor.registerAccount(request);
        return accountResponse;
    }

    public List<AccountResponse> getAccounts() {

        return accountProcessor.getAccounts();
    }




}
