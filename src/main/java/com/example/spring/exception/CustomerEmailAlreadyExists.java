package com.example.spring.exception;

public class CustomerEmailAlreadyExists extends ResourceTakenExeption{

    public CustomerEmailAlreadyExists() {
    }

    public CustomerEmailAlreadyExists(String message) {
        super(message);
    }
}
