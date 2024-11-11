package com.example.user.service;

import com.example.user.dto.UserDTO;
import com.example.user.dto.request.AuthRequest;
import com.example.user.dto.response.ApiResponse;

import java.util.List;


public interface UserService {
    UserDTO updateUser(UserDTO user);

    List<UserDTO> getAllUsers();

    UserDTO createUser(UserDTO user);

    ApiResponse login(AuthRequest authRequest) throws Exception;
}
