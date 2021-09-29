package com.bank.controller;

import com.bank.bean.request.AccountRequest;
import com.bank.bean.response.AccountResponse;
import com.bank.facade.AccountFacade;
import com.bank.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountFacade facade;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody AccountRequest request) {

        try {
            AccountResponse response = facade.registerAccount(request);
            return ResponseEntity.ok(response);

        }catch(ValidationException ev){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(ev.getLocalizedMessage());
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ex.getMessage());
        }
    }


    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {

        try {
            List<AccountResponse> response = facade.getAccounts();
            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ex.getLocalizedMessage());
        }
    }
}
