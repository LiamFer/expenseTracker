package com.liamfer.expenseTracker.DTO;

import com.liamfer.expenseTracker.enums.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ExpenseDTO(@NotBlank String title,
                         @NotNull ExpenseCategory category,
                         @Positive double price,
                         @NotNull LocalDateTime date) {

}
