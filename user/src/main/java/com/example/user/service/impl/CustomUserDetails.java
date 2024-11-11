package com.example.user.service.impl;

import com.example.user.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the authorities/roles for the user
        return Collections.singletonList(() -> "ROLE_USER"); // Example role, adjust as needed
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword(); // Return the user's password
    }

    @Override
    public String getUsername() {
        return userEntity.getUserName(); // Return the user's username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assume the account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assume the account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assume the credentials are not expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Return whether the account is enabled
    }

}
