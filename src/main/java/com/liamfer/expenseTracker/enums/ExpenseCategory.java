package com.liamfer.expenseTracker.enums;

public enum ExpenseCategory {
    Groceries("Groceries"),
    Leisure("Leisure"),
    Eletronics("Eletronics"),
    Utilities("Utilities"),
    Clothing("Clothing"),
    Health("Health"),
    Others("Others");

    private final String category;

    ExpenseCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}