package com.project.financial_tracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExistingUserException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
