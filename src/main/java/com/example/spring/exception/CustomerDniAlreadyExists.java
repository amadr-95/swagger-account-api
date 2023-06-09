package com.example.spring.exception;

public class CustomerDniAlreadyExists extends ResourceTakenExeption{

    public CustomerDniAlreadyExists() {
    }

    public CustomerDniAlreadyExists(String message) {
        super(message);
    }
}
