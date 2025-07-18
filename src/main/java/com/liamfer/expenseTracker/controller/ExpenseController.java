package com.liamfer.expenseTracker.controller;

import com.liamfer.expenseTracker.DTO.ExpenseDTO;
import com.liamfer.expenseTracker.DTO.UpdateExpenseDTO;
import com.liamfer.expenseTracker.domain.ExpenseEntity;
import com.liamfer.expenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseEntity>> getExpenses(@AuthenticationPrincipal UserDetails user,
                                                           Pageable page,
                                                           @RequestParam(name = "pastWeek",defaultValue = "false") boolean pastWeek,
                                                           @RequestParam(name = "pastMonth",defaultValue = "false") boolean pastMonth,
                                                           @RequestParam(name = "lastThreeMonths",defaultValue = "false") boolean lastThreeMonths,
                                                           @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                           @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate)
    {
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.getExpenses(user,page,pastWeek,pastMonth,lastThreeMonths,startDate,endDate));
    }

    @PostMapping
    public ResponseEntity<ExpenseEntity> addExpense(@RequestBody @Valid ExpenseDTO expense, @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(expense,user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExpenseEntity> updateExpense(@PathVariable("id") Long id, @RequestBody UpdateExpenseDTO expense, @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.updateExpense(id,expense,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeExpense(@PathVariable("id") Long id,@AuthenticationPrincipal UserDetails user){
        expenseService.deleteExpense(id,user);
        return ResponseEntity.noContent().build();
    }

}
