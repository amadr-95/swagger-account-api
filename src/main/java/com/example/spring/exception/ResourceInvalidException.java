package com.example.spring.exception;

public class ResourceInvalidException extends RuntimeException{

    public ResourceInvalidException() {
    }

    public ResourceInvalidException(String message) {
        super(message);
    }
}
