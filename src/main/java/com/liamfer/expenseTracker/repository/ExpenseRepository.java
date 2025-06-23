package com.liamfer.expenseTracker.repository;
import com.liamfer.expenseTracker.domain.ExpenseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {
    Page<ExpenseEntity> findAllByOwnerEmail(String email, Pageable page);
}
