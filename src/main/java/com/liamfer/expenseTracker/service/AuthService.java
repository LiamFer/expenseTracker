package com.liamfer.expenseTracker.service;

import com.liamfer.expenseTracker.DTO.UserDTO;
import com.liamfer.expenseTracker.domain.UserEntity;
import com.liamfer.expenseTracker.enums.UserRoles;
import com.liamfer.expenseTracker.exceptions.EmailAlreadyInUseException;
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

    public UserDetails createUser(UserDTO user){
        if(userRepository.findByEmail(user.email()) != null) throw new EmailAlreadyInUseException("Email já está em uso.");
        String hashedPassword = cryptPasswordEncoder.encode(user.password());
        return userRepository.save(new UserEntity(user.name(),user.email(),hashedPassword,UserRoles.STANDARD));
    }
}
