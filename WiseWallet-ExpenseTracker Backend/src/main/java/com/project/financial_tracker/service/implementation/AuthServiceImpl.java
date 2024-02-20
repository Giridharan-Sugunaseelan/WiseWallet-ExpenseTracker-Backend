package com.project.financial_tracker.service.implementation;

import com.project.financial_tracker.dto.JwtTokenDto;
import com.project.financial_tracker.dto.LoginDto;
import com.project.financial_tracker.dto.RegisterDto;
import com.project.financial_tracker.dto.ResetPasswordDto;
import com.project.financial_tracker.exception.ExistingUserException;
import com.project.financial_tracker.model.User;
import com.project.financial_tracker.repository.UserRepository;
import com.project.financial_tracker.security.configuration.JwtTokenProvider;
import com.project.financial_tracker.service.interfaces.AuthService;
import com.project.financial_tracker.util.Principal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    @Override
    public String register(RegisterDto dto) {
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new ExistingUserException(HttpStatus.BAD_REQUEST,"User with the given email already exists");
        }
        else{
            System.out.println(dto);
            User newUser = new User();
            newUser.setFirstName(dto.getFirstName());
            newUser.setLastName(dto.getLastName());
            newUser.setEmail(dto.getEmail());
            newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(newUser);
        }
        return "User created successfully";
    }

    @Override
    public JwtTokenDto login(LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        JwtTokenDto tokenDto = new JwtTokenDto(token);

        return tokenDto;
    }

    @Override
    public String resetPassword(ResetPasswordDto dto) {
        String email = Principal.getAuthentication().getName();
        User user = this.userRepository.findByEmail(email);
        String newPassword = this.passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(newPassword);
        this.userRepository.save(user);
        return "password changed successfully";
    }
}
