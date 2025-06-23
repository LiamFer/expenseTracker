package com.liamfer.expenseTracker.DTO;

import org.springframework.http.HttpStatus;

public record ResponseMessage(int code,String message) {
}
