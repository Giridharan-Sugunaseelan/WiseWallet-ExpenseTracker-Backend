package com.project.financial_tracker.service.implementation;

import com.project.financial_tracker.dto.ResetPasswordDto;
import com.project.financial_tracker.exception.UserNotFoundException;
import com.project.financial_tracker.model.PasswordResetToken;
import com.project.financial_tracker.model.User;
import com.project.financial_tracker.repository.UserRepository;
import com.project.financial_tracker.util.PasswordResetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetUtil tokenUtil;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder encoder;

    public String sendResetLink(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User doesn't exist with the given email");
        }
        PasswordResetToken resetToken = this.tokenUtil.createToken(user);
        String resetUrl = "http://localhost:5173/wisewallet/resetPassword?token=" + resetToken.getToken();
        String mailBody = "Hi " + user.getFirstName() + ", follow the link to reset your password - " + resetUrl;
        String mailSubject = "WiseWallet - Reset Password";
        emailService.sendResetPasswordMail(email, mailSubject, mailBody);
        return "Password reset link sent to " + email;
    }

    public String resetPassword(String token, ResetPasswordDto dto){
        PasswordResetToken resetToken = this.tokenUtil.getByToken(token);
        if(resetToken == null || resetToken.getExpireAt().isBefore(LocalDateTime.now())){
            return "The link is invalid or expired";
        }
        String password = dto.getNewPassword();
        User user = resetToken.getUser();
        user.setPassword(this.encoder.encode(password));
        this.userRepository.save(user);
        this.tokenUtil.deleteToken(resetToken);
        return "Password Changed Successfully";
    }
}
