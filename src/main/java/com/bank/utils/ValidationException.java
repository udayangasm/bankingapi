package com.bank.utils;

public class ValidationException extends RuntimeException{

    public ValidationException (String errorMessage ){
        super(errorMessage);
    }
}
