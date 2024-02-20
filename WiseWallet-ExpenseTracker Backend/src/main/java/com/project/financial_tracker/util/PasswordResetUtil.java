package com.project.financial_tracker.util;

import com.project.financial_tracker.dto.UserDto;
import com.project.financial_tracker.model.PasswordResetToken;
import com.project.financial_tracker.model.User;
import com.project.financial_tracker.security.repository.PasswordResetRepository;
import com.project.financial_tracker.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetUtil {

    @Autowired
    private PasswordResetRepository resetRepository;

    public PasswordResetToken createToken(User user) {
        PasswordResetToken existingToken = this.resetRepository.findByUser(user);
        if (existingToken != null) {
            this.resetRepository.delete(existingToken);
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setTimeStamp(LocalDateTime.now());
        resetToken.setExpireAt(LocalDateTime.now().plusMinutes(15));
        resetToken.setUser(user);
        return this.resetRepository.save(resetToken);
    }

    public PasswordResetToken getByToken(String token) {
        return this.resetRepository.findByToken(token);
    }

    public void deleteToken(PasswordResetToken token) {
        this.resetRepository.delete(token);
    }
}
