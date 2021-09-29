package com.bank.controller;

import com.bank.bean.request.TransactionRequest;
import com.bank.bean.request.TransferRequest;
import com.bank.bean.response.AccountBalanceResponse;
import com.bank.bean.response.AccountResponse;
import com.bank.bean.response.TransactionResponse;
import com.bank.facade.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionFacade facade;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionRequest request) {
        try {
            TransactionResponse response = facade.deposit(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ex.getLocalizedMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionRequest request) {
        try {
            TransactionResponse response = facade.withdraw(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ex.getLocalizedMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) {
        try {
            TransactionResponse response = facade.transfer(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<?> checkBalance(@RequestParam(name = "id") String accountId) {
        try {
            AccountBalanceResponse accountBalanceResponse = facade.checkBalance(accountId);
            return ResponseEntity.ok(accountBalanceResponse);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ex.getLocalizedMessage());
        }
    }
}
