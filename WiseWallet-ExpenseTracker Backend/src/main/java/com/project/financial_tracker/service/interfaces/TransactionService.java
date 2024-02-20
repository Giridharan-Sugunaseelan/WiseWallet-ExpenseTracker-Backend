package com.project.financial_tracker.service.interfaces;

import com.project.financial_tracker.dto.TransactionDto;


import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
//
//    List<TransactionDto> findByCategory(String category);
//
//    List<TransactionDto> findByDescription(String description);
//
//    List<TransactionDto> findByDate(LocalDate date);
//
//    List<TransactionDto> findByDateBeforeAndDateAfter(LocalDate startDate, LocalDate endDate);
//
//    List<TransactionDto> findByAmount(Double amount);

    List<TransactionDto> getUserTransaction();

    TransactionDto addTransaction(TransactionDto transactions);

    TransactionDto updateTransaction(TransactionDto transactionDto);

    void deleteTransaction(Integer id);
}
