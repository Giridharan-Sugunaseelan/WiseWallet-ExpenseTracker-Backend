package com.project.financial_tracker.security.configuration;

import com.project.financial_tracker.model.User;
import com.project.financial_tracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

   private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = this.repository.findByEmail(username);
        List<GrantedAuthority> authorities = null;
        if(user != null){
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
        }else {
            throw new UsernameNotFoundException("User not found!!!");
        }
    }
}
