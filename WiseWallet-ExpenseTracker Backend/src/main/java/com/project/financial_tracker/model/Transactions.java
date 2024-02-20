package com.project.financial_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactional_id")
    private Integer id;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String type;
    private String description;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public Transactions(Integer id, Double amount, String category, String type, String description, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.description = description;
        this.date = date;
    }
}
