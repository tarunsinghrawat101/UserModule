package com.example.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @NotBlank(message = "UserName can't be null")
    private String userName;
    @NotBlank(message = "Password can't be null")
    private String password;
}
