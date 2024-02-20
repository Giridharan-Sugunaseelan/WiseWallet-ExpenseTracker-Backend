package com.project.financial_tracker.security.controllers;

import com.project.financial_tracker.dto.ResetPasswordDto;
import com.project.financial_tracker.service.implementation.PasswordResetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetController {

    @Autowired
    private PasswordResetService service;

    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email){
        String linkStatus = this.service.sendResetLink(email);
        return new ResponseEntity<>(linkStatus, HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestBody ResetPasswordDto dto){
        String status = this.service.resetPassword(token, dto);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }
}

