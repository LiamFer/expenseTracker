package com.liamfer.expenseTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @PostMapping
    public ResponseEntity<?> addExpense(){
        return ResponseEntity.ok("oi");
    }
}
