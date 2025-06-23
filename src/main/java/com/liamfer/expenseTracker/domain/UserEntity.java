package com.liamfer.expenseTracker.domain;


import com.liamfer.expenseTracker.enums.UserRoles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_tb")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public String nome;
    public String email;
    public String password;
    public UserRoles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role.equals(UserRoles.ADMIN)) return List.of(new SimpleGrantedAuthority(UserRoles.STANDARD.name()),new SimpleGrantedAuthority(UserRoles.ADMIN.name()));
        return List.of(new SimpleGrantedAuthority(UserRoles.STANDARD.name()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
