package com.project.financial_tracker.service.implementation;

import com.project.financial_tracker.dto.TransactionDto;
import com.project.financial_tracker.dto.UserDto;
import com.project.financial_tracker.exception.UserNotFoundException;
import com.project.financial_tracker.model.Transactions;
import com.project.financial_tracker.model.User;
import com.project.financial_tracker.repository.UserRepository;
import com.project.financial_tracker.service.interfaces.UserService;
import com.project.financial_tracker.util.Principal;
import com.project.financial_tracker.util.TransactionsMapper;
import com.project.financial_tracker.util.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public UserDto getUser(String email) {
        User user = this.repository.findByEmail(email);
        return UserMapper.mapToDto(user);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> all = this.repository.findAll();
        return all.stream().map((user) -> UserMapper.mapToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        String email = Principal.getAuthentication().getName();
        User user = this.repository.findByEmail(email);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        this.repository.save(user);
        return UserMapper.mapToDto(user);
    }

    @Override
    public void deleteUser(String email) {
        this.repository.deleteByEmail(email);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
