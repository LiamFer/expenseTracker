package com.liamfer.expenseTracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiError(
        @JsonFormat(pattern="dd/MM/yyyy HH:mm")
        LocalDateTime date,
        HttpStatus code,
        String status,
        List<String> errors
) {
}
