package com.liamfer.expenseTracker.service;

import com.liamfer.expenseTracker.DTO.UserDTO;
import com.liamfer.expenseTracker.DTO.UserLoginDTO;
import com.liamfer.expenseTracker.domain.UserEntity;
import com.liamfer.expenseTracker.enums.UserRoles;
import com.liamfer.expenseTracker.exceptions.CredenciaisInvalidasException;
import com.liamfer.expenseTracker.exceptions.EmailAlreadyInUseException;
import com.liamfer.expenseTracker.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService  {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public UserDetails createUser(UserDTO user){
        if(userRepository.findByEmail(user.email()) != null) throw new EmailAlreadyInUseException("Email já está em uso.");
        String hashedPassword = passwordEncoder.encode(user.password());
        return userRepository.save(new UserEntity(user.name(),user.email(),hashedPassword,UserRoles.STANDARD));
    }

    public String loginUser(UserLoginDTO user){
        try{
            var authToken = new UsernamePasswordAuthenticationToken(user.email(),user.password());
            Authentication auth = authenticationManager.authenticate(authToken);
            UserEntity authUser = (UserEntity) auth.getPrincipal();
            return jwtService.generateToken(authUser);
        } catch (Exception ex){
            throw new CredenciaisInvalidasException("Usuário ou Senha inválidos");
        }
    }


}
