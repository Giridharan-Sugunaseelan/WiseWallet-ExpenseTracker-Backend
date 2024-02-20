package com.project.financial_tracker.service.implementation;

import com.project.financial_tracker.dto.TransactionDto;
import com.project.financial_tracker.model.Transactions;
import com.project.financial_tracker.model.User;
import com.project.financial_tracker.repository.TransactionsRepository;
import com.project.financial_tracker.repository.UserRepository;
import com.project.financial_tracker.service.interfaces.TransactionService;
import com.project.financial_tracker.util.Principal;
import com.project.financial_tracker.util.TransactionsMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionsRepository repository;

    private UserRepository userRepository;

//    @Override
//    public List<TransactionDto> findByCategory(String category) {
//        List<Transactions> transactions = this.repository.findByCategory(category);
//        return transactions.stream().map((transaction) -> TransactionsMapper.mapToDto(transaction)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TransactionDto> findByDescription(String description) {
//        List<Transactions> transactions = this.repository.findByDescription(description);
//        return transactions.stream().map((transaction) -> TransactionsMapper.mapToDto(transaction)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TransactionDto> findByDate(LocalDate date) {
//        List<Transactions> transactions = this.repository.findByDate(date);
//        return transactions.stream().map((transaction) -> TransactionsMapper.mapToDto(transaction)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TransactionDto> findByDateBeforeAndDateAfter(LocalDate startDate, LocalDate endDate) {
//        List<Transactions> transactions = this.repository.findByDateBeforeAndDateAfter(startDate, endDate);
//        return transactions.stream().map((transaction) -> TransactionsMapper.mapToDto(transaction)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TransactionDto> findByAmount(Double amount) {
//        List<Transactions> transactions = this.repository.findByAmount(amount);
//        return transactions.stream().map((transaction) -> TransactionsMapper.mapToDto(transaction)).collect(Collectors.toList());
//    }

    @Override
    public List<TransactionDto>getUserTransaction(){
        String email = Principal.getAuthentication().getName();
        List<Transactions> transactions = this.userRepository.findByEmail(email).getTransactions();
        return transactions.stream().map((transaction) -> TransactionsMapper.mapToDto(transaction)).collect(Collectors.toList());
    }
    @Override
    public TransactionDto addTransaction(TransactionDto dto) {
        Transactions transaction = TransactionsMapper.mapToEntity(dto);
        String email = Principal.getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        transaction.setUser(user);
        if(dto.getType().equalsIgnoreCase("income")){
            user.setBalance(user.getBalance() + transaction.getAmount());
        }
        else {
            user.setBalance(user.getBalance() - transaction.getAmount());
        }
        transaction.getUser().getTransactions().add(transaction);
        Transactions saved = this.repository.save(transaction);
        userRepository.save(user);
        return TransactionsMapper.mapToDto(saved);
    }

    @Override
    public TransactionDto updateTransaction(TransactionDto dto) {
        Transactions transaction = TransactionsMapper.mapToEntity(dto);
        Transactions tobeUpdated = this.repository.findById(dto.getId()).get();
        String email = Principal.getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        transaction.setUser(user);
        if(transaction.getCategory().equalsIgnoreCase("income")){
            Double updateTransaction = transaction.getAmount();
            Double previousTransaction = tobeUpdated.getAmount();
            Double amountToBeAdded = updateTransaction - previousTransaction;
            user.setBalance(user.getBalance() + amountToBeAdded);
            userRepository.save(user);
            tobeUpdated.setAmount(updateTransaction);
        }
        else{
            Double updateTransaction = transaction.getAmount();
            Double previousTransaction = tobeUpdated.getAmount();
            Double amountToBeAdded = updateTransaction - previousTransaction;
            user.setBalance(user.getBalance() - amountToBeAdded);
            userRepository.save(user);
            tobeUpdated.setAmount(updateTransaction);
        }
        return TransactionsMapper.mapToDto(this.repository.save(tobeUpdated));
    }

    @Override
    public void deleteTransaction(Integer id) {
        Transactions transaction = this.repository.findById(id).get();
        String email = Principal.getAuthentication().getName();
        User user = this.userRepository.findByEmail(email);
        if(transaction.getType().equalsIgnoreCase("expense")){
            user.setBalance(user.getBalance() + transaction.getAmount());
        }
        else{
            user.setBalance(user.getBalance() - transaction.getAmount());
        }
        this.userRepository.save(user);
        this.repository.deleteById(transaction.getId());
    }
}
