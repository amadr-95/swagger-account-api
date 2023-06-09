package com.example.spring.exception;

public class ResourceTakenExeption extends RuntimeException{

    public ResourceTakenExeption() {
    }

    public ResourceTakenExeption(String message) {
        super(message);
    }
}
