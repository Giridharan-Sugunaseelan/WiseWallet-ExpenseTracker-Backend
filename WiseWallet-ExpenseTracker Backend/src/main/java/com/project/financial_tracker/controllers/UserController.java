package com.project.financial_tracker.controllers;

import com.project.financial_tracker.dto.TransactionDto;
import com.project.financial_tracker.dto.UserDto;
import com.project.financial_tracker.service.interfaces.TransactionService;
import com.project.financial_tracker.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService service;

    private TransactionService transactionService;


    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto user = this.service.getUser(email);
        System.out.println(email);
        System.out.println(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

//    @GetMapping("transactions/{id}")
//    public ResponseEntity<List<TransactionDto>> getUserTransactions(@PathVariable Integer id){
//        List<TransactionDto> userTransaction = this.service.getUserTransaction(id);
//        return ResponseEntity.ok(userTransaction);
//    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto){
        UserDto userDto = this.service.updateUser(dto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email){
        this.service.deleteUser(email);
        return ResponseEntity.ok("User Deleted Successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll() {
        this.service.deleteAll();
        return ResponseEntity.ok("All Users deleted successfully");
    }
}
