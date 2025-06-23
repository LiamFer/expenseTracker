package com.liamfer.expenseTracker.domain;

import com.liamfer.expenseTracker.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_tb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public double price;
    public ExpenseCategory category;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    public UserEntity owner;
}
