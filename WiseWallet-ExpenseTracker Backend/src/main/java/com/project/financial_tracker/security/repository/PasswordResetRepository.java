package com.project.financial_tracker.security.repository;

import com.project.financial_tracker.model.PasswordResetToken;
import com.project.financial_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken,Integer> {
    PasswordResetToken findByUser(User user);
    PasswordResetToken findByToken(String token);
}
