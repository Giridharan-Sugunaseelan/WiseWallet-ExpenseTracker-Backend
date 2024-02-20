package com.project.financial_tracker.service.interfaces;

import com.project.financial_tracker.dto.TransactionDto;
import com.project.financial_tracker.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(String email);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto dto);
    void deleteUser(String email);
    void deleteAll();

}
