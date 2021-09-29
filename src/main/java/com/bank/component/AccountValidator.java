package com.bank.component;

import com.bank.bean.request.AccountRequest;
import com.bank.repository.AccountRepository;
import com.bank.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class AccountValidator {

    @Autowired
    private AccountRepository accountRepository;

    public void validateRegisterAccount(AccountRequest request){

        if (ObjectUtils.isEmpty(request.getName())){
            throw new ValidationException("Error: Name is empty");
        }else if(ObjectUtils.isEmpty(request.getAccountId())){
            throw new ValidationException("Error: Account ID is empty");
        }else{
            if(accountRepository.existsByAccountId(request.getAccountId())){
                throw new ValidationException("Error: Account ID is already exists");
            }
        }
    }
}
