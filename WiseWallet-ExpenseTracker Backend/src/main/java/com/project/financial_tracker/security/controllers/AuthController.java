package com.project.financial_tracker.security.controllers;

import com.project.financial_tracker.dto.JwtTokenDto;
import com.project.financial_tracker.dto.LoginDto;
import com.project.financial_tracker.dto.RegisterDto;
import com.project.financial_tracker.dto.ResetPasswordDto;
import com.project.financial_tracker.service.interfaces.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto){
        String status = this.service.register(dto);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto dto){
        JwtTokenDto login = this.service.login(dto);
        return new ResponseEntity<>(login,HttpStatus.ACCEPTED);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto dto){
        String response = this.service.resetPassword(dto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
