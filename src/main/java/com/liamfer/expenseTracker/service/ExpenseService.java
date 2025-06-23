package com.liamfer.expenseTracker.service;

import com.liamfer.expenseTracker.DTO.ExpenseDTO;
import com.liamfer.expenseTracker.domain.ExpenseEntity;
import com.liamfer.expenseTracker.domain.UserEntity;
import com.liamfer.expenseTracker.exceptions.ResourceNotFoundException;
import com.liamfer.expenseTracker.repository.ExpenseRepository;
import com.liamfer.expenseTracker.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Page<ExpenseEntity> getExpenses(UserDetails user, Pageable page) {
        return expenseRepository.findAllByOwnerEmail(user.getUsername(), page);
    }

    public ExpenseEntity createExpense(ExpenseDTO expense, UserDetails user) {
        UserEntity owner = userRepository.findByEmail(user.getUsername());
        return expenseRepository.save(new ExpenseEntity(expense.title(), expense.price(), expense.category(), expense.date(), owner));
    }

    public void deleteExpense(Long id, UserDetails user) {
        ExpenseEntity expense = this.findExpense(id,user);
        expenseRepository.deleteById(id);
    }

    public ExpenseEntity findExpense(Long id,UserDetails user) {
        Optional<ExpenseEntity> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            if (expense.get().owner.email.equals(user.getUsername())) {
                return expense.get();
            }
            throw new ResourceAccessException("Unauthorized Resource Access");
        }
        throw new ResourceNotFoundException("Despesa não encontrada");
    }

}
