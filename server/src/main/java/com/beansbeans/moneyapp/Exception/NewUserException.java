package com.beansbeans.moneyapp.Exception;

public class NewUserException extends IllegalArgumentException {

    private final String errorMessage;

    public NewUserException(String message){
        super();
        this.errorMessage = message;

    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
