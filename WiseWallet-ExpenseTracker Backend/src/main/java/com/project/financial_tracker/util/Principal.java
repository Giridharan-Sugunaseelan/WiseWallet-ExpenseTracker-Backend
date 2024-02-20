package com.project.financial_tracker.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Principal {
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
