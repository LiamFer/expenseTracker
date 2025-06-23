package com.liamfer.expenseTracker.controller;

import com.liamfer.expenseTracker.DTO.TokenResponseDTO;
import com.liamfer.expenseTracker.DTO.ResponseMessage;
import com.liamfer.expenseTracker.DTO.UserDTO;
import com.liamfer.expenseTracker.DTO.UserLoginDTO;
import com.liamfer.expenseTracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody @Valid UserDTO user){
        authService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpStatus.CREATED.value(),"Usuário criado"));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UserLoginDTO user){
        String token = authService.loginUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TokenResponseDTO(HttpStatus.OK.value(),"Login Realizado",token));
    }
}
