package com.example.spring.exception;

public class UserOrEmailException extends AccountNotFoundException {

    public UserOrEmailException() {
    }

    public UserOrEmailException(String message) {
        super(message);
    }
}
