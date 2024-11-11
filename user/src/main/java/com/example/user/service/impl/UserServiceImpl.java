package com.example.user.service.impl;

import com.example.user.dto.UserDTO;
import com.example.user.dto.request.AuthRequest;
import com.example.user.dto.response.ApiResponse;
import com.example.user.entity.UserEntity;
import com.example.user.handler.UserAlreadyExistsException;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import com.example.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDTO createUser(UserDTO user) {
        Optional<UserEntity> userFound = userRepository.findByUserName(user.getUsername());
        if (userFound.isPresent()) {
            throw new UserAlreadyExistsException("User already registered with the provided user name: " + user.getUsername());
        }
        UserEntity newUser = userMapper.mapToEntity(new UserEntity(), user);
        UserEntity savedNewUser = userRepository.save(newUser);
        return userMapper.mapToDTO(savedNewUser);
    }

    @Override
    public ApiResponse login(AuthRequest authRequest) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        if (userDetails != null) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            } catch (DisabledException e) {
                return new ApiResponse("USER_DISABLED WITH EXCEPTION" + e.getMessage(), false);
            } catch (BadCredentialsException e) {
                return new ApiResponse("INVALID_CREDENTIALS WITH EXCEPTION" + e.getMessage(), false);
            }
            final String jwtToken = jwtUtil.generateJwtToken(userDetails);
            return new ApiResponse(jwtToken, true);
        } else
            return new ApiResponse("User Name " + authRequest.getUserName() + " Not Found ", false);
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        Optional<UserEntity> userFound = userRepository.findByUserName(user.getUsername());
        if (userFound.isEmpty()) {
            UserEntity savedUser = userRepository.save(userMapper.mapToEntity(new UserEntity(), user));
            return userMapper.mapToDTO(savedUser);
        }
        UserEntity foundUser = userMapper.mapToEntity(userFound.get(), user);
        UserEntity updatedUser = userRepository.save(foundUser);
        return userMapper.mapToDTO(updatedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream().map(user -> userMapper.mapToDTO(user))
                .toList();
    }
}
