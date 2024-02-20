package com.project.financial_tracker.repository;

import com.project.financial_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    void deleteByEmail(String email);
    boolean existsByEmail(String email);
}
