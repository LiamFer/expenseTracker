package com.liamfer.expenseTracker.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(
                      @NotBlank(message = "O e-mail é obrigatório")
                      @Email(message = "E-mail inválido")
                      String email,
                      @NotBlank(message = "A senha é obrigatória")
                      @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
                      String password) {
}
