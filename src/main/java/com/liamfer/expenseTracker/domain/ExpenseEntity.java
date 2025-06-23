package com.liamfer.expenseTracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liamfer.expenseTracker.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    public LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    public UserEntity owner;

    public ExpenseEntity(String title,double price,ExpenseCategory category,LocalDateTime date,UserEntity owner){
        this.title = title;
        this.price = price;
        this.category = category;
        this.date = date;
        this.owner = owner;
    }

}
