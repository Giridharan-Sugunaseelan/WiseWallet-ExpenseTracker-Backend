package com.project.financial_tracker.model;

import com.project.financial_tracker.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private LocalDateTime timeStamp;
    private LocalDateTime expireAt;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    private User user;

    public PasswordResetToken(String token, LocalDateTime timeStamp, LocalDateTime expireAt, User user) {
        this.token = token;
        this.timeStamp = timeStamp;
        this.expireAt = expireAt;
        this.user = user;
    }
}
