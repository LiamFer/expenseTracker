package com.liamfer.expenseTracker.DTO;

public record UserDTO (String name,String email,String password) {
    public UserDTO(String email, String password) {
        this(null, email, password);
    }
}
