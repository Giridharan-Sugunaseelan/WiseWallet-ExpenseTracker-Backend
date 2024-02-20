package com.project.financial_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class JwtTokenDto {
    private String accessToken;
    private final String tokenType = "Bearer ";
    public JwtTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
