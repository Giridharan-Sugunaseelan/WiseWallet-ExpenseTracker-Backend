package com.project.financial_tracker.repository;

import com.project.financial_tracker.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
    List<Transactions> findByCategory(String category);

    List<Transactions> findByDescription(String description);

    List<Transactions> findByDate(LocalDate date);

    List<Transactions> findByDateBeforeAndDateAfter(LocalDate startDate, LocalDate endDate);

    List<Transactions> findByAmount(Double amount);

}
