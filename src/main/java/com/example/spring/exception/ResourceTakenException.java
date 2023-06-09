package com.example.spring.exception;

public class ResourceTakenException extends ResourceInvalidException{

    public ResourceTakenException() {
    }

    public ResourceTakenException(String message) {
        super(message);
    }
}
