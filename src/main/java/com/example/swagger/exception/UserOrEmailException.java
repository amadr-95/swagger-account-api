package com.example.swagger.exception;

public class UserOrEmailException extends AccountNotFoundException {

    public UserOrEmailException() {
    }

    public UserOrEmailException(String message) {
        super(message);
    }
}
