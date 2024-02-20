package com.project.financial_tracker.security.configuration;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {


    private CustomUserDetailsService service;


    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = this.service.loadUserByUsername(userName);
        if(user != null){
            if(encoder.matches(password,user.getPassword())){
                return new UsernamePasswordAuthenticationToken(userName,password,user.getAuthorities());
            }
            else{
                throw new BadCredentialsException("Incorrect username or password");
            }
        }
        throw new BadCredentialsException("User doesn't exist");
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
