package com.example.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> accountNotFoundException(AccountNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (ex instanceof UserOrEmailException || ex instanceof UserOrEmailTaken)
            status = HttpStatus.BAD_REQUEST;

        ErrorMessage message = new ErrorMessage(
                status.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, status);
    }

}
