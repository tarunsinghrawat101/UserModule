package com.example.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String role;
    private String firstName;
    private String middleName;
    private String lastName;
    private Boolean passwordRest;
    private String email;
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
