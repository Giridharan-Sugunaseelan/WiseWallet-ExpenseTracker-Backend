package com.project.financial_tracker.controllers;

import com.project.financial_tracker.dto.TransactionDto;
import com.project.financial_tracker.service.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionsController {
    private TransactionService service;

//    @GetMapping("/{category}")
//    public ResponseEntity<List<TransactionDto>> findByCategory(@PathVariable String category){
//        List<TransactionDto> transactionDtos = this.service.findByCategory(category);
//        return ResponseEntity.ok(transactionDtos);
//    }
//
//    @PostMapping("/description")
//    public ResponseEntity<List<TransactionDto>> findByDescription(@RequestBody String description){
//        List<TransactionDto> transactionDtos = this.service.findByDescription(description);
//        return ResponseEntity.ok(transactionDtos);
//    }
//
//    @GetMapping("/{date}")
//    public ResponseEntity<List<TransactionDto>> findByDate(@PathVariable LocalDate date){
//        List<TransactionDto> transactionDtos = this.service.findByDate(date);
//        return ResponseEntity.ok(transactionDtos);
//    }
//
//    @GetMapping("/{amount}")
//    public ResponseEntity<List<TransactionDto>> findByAmount(@PathVariable Double amount){
//        List<TransactionDto> transactionDtos = this.service.findByAmount(amount);
//        return ResponseEntity.ok(transactionDtos);
//    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> getTransactions(){
        List<TransactionDto> userTransactions = this.service.getUserTransaction();
        return new ResponseEntity<>(userTransactions, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto dto){
        TransactionDto save = this.service.addTransaction(dto);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/update")
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto dto){
        TransactionDto save = this.service.updateTransaction(dto);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Integer id){
        this.service.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}
