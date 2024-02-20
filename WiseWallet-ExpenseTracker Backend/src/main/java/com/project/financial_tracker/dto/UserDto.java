package com.project.financial_tracker.dto;

import com.project.financial_tracker.model.Transactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

//    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Double balance;
}
