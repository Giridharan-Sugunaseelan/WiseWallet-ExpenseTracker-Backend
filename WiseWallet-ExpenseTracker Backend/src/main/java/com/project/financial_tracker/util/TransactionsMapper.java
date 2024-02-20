package com.project.financial_tracker.util;

import com.project.financial_tracker.dto.TransactionDto;
import com.project.financial_tracker.model.Transactions;
import com.project.financial_tracker.model.User;
import com.project.financial_tracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TransactionsMapper {

    public static TransactionDto mapToDto(Transactions transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getType(),
                transaction.getDescription(),
                transaction.getDate()
        );
    }

    public static Transactions mapToEntity(TransactionDto dto){
        return new Transactions(
          dto.getId(),
          dto.getAmount(),
          dto.getCategory(),
          dto.getType(),
          dto.getDescription(),
          LocalDate.now()
        );
    }
}
