package com.liamfer.expenseTracker.service;

import com.liamfer.expenseTracker.DTO.ExpenseDTO;
import com.liamfer.expenseTracker.domain.ExpenseEntity;
import com.liamfer.expenseTracker.domain.UserEntity;
import com.liamfer.expenseTracker.repository.ExpenseRepository;
import com.liamfer.expenseTracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public ExpenseEntity createExpense(ExpenseDTO expense, UserDetails user){
        UserEntity owner = userRepository.findByEmail(user.getUsername());
        return expenseRepository.save(new ExpenseEntity(expense.title(),expense.price(),expense.category(), expense.date(),owner));
    }

}
