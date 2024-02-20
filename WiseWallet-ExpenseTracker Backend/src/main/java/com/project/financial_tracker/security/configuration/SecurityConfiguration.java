package com.project.financial_tracker.security.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private JwtAuthenticationEntryPoint entryPoint;
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)-> csrf.disable()).authorizeHttpRequests((request)->{
//            request.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
//            request.requestMatchers(HttpMethod.POST, "/transactions/**","/user/**").authenticated();
//            request.requestMatchers(HttpMethod.GET, "/transactions/**","/user/**").authenticated();
//            request.requestMatchers(HttpMethod.DELETE, "/transactions/**","/user/**").authenticated();
//            request.requestMatchers("/dashboard").authenticated();
            request.requestMatchers("/home", "/about","/register","/login","/forgotPassword/**", "/resetPassword/**").permitAll();
            request.anyRequest().authenticated();
            }
        ).httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(entryPoint))
                .addFilterBefore(
                        authenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

