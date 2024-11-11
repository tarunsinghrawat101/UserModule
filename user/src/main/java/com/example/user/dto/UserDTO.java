package com.example.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "User Name can't be null")
    private String username;
    @NotBlank(message = "Password can't be null")
    private String password;

    private String role;
    private String firstName;
    private String middleName;
    private String lastName;
    private Boolean passwordRest;
    private String email;

    @NotBlank(message = "Contact number cannot be null")
    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits")
    private String contact;

    private String expirationDate;
    private String description;
    private String timeOut;
    private String dateTimeFormat;
    private String primaryGroupName;
    private String language;
    private String organisation;
    private String timeZone;
    private String memo;
}
