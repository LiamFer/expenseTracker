package com.liamfer.expenseTracker.DTO;

import com.liamfer.expenseTracker.enums.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record UpdateExpenseDTO(@NotNull Long id,
                               @NotBlank String title,
                               @NotNull ExpenseCategory category,
                               @Positive Double price,
                               @NotNull LocalDateTime date) {

}
