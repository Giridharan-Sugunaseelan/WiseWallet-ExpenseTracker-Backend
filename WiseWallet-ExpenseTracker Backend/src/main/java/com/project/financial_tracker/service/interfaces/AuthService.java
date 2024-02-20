package com.project.financial_tracker.service.interfaces;

import com.project.financial_tracker.dto.JwtTokenDto;
import com.project.financial_tracker.dto.LoginDto;
import com.project.financial_tracker.dto.RegisterDto;
import com.project.financial_tracker.dto.ResetPasswordDto;

public interface AuthService {
    String register(RegisterDto dto);

    JwtTokenDto login(LoginDto dto);

    String resetPassword(ResetPasswordDto password);
}
