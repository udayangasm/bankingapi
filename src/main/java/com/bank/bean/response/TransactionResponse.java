package com.bank.bean.response;

import org.springframework.http.HttpStatus;

public class TransactionResponse {

    private long id;
    private String result;
    private HttpStatus resultCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public HttpStatus getResultCode() {
        return resultCode;
    }

    public void setResultCode(HttpStatus resultCode) {
        this.resultCode = resultCode;
    }
}
