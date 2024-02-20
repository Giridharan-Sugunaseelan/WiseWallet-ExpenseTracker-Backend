package com.project.financial_tracker.exception;

import com.project.financial_tracker.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistingUserException.class)
    public ResponseEntity<Error> ExistingUserExceptionHandler
            (ExistingUserException exception, WebRequest request) {
        Error error = new Error(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Error> BadCredentialsExceptionHandler(BadCredentialsException exception, WebRequest request){
        Error error = new Error(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> UserNotFoundExceptionHandler(UserNotFoundException exception, WebRequest request){
        Error error = new Error(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
