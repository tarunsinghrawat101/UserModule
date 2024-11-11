package com.example.user.controller;

import com.example.user.dto.UserDTO;
import com.example.user.dto.request.AuthRequest;
import com.example.user.dto.response.ApiResponse;
import com.example.user.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        return userService.login(authRequest);
    }

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/updateUser")
    public UserDTO updateUser(@Valid @RequestBody UserDTO user) {
        return userService.updateUser(user);
    }
}
