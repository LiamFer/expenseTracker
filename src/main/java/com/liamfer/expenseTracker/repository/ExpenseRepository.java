package com.liamfer.expenseTracker.repository;
import com.liamfer.expenseTracker.domain.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
}
