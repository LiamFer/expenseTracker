package com.liamfer.expenseTracker.service;

import com.liamfer.expenseTracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private BCryptPasswordEncoder cryptPasswordEncoder;
    private UserRepository userRepository;

    public AuthService(BCryptPasswordEncoder cryptPasswordEncoder, UserRepository userRepository) {
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.userRepository = userRepository;
    }


}
