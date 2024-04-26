package com.example.swagger.exception;

public class UserOrEmailTaken extends AccountNotFoundException {

    public UserOrEmailTaken() {
    }

    public UserOrEmailTaken(String message) {
        super(message);
    }
}
