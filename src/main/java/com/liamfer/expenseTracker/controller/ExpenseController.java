package com.liamfer.expenseTracker.controller;

import com.liamfer.expenseTracker.DTO.ExpenseDTO;
import com.liamfer.expenseTracker.domain.ExpenseEntity;
import com.liamfer.expenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseEntity> addExpense(@RequestBody @Valid ExpenseDTO expense, @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(expense,user));
    }
}
