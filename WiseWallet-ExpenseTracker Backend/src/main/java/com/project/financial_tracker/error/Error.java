package com.project.financial_tracker.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Error {
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
