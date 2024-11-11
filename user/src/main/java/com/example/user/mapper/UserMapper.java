package com.example.user.mapper;

import com.example.user.dto.UserDTO;
import com.example.user.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO mapToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUserName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(userEntity.getRole());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setMiddleName(userEntity.getMiddleName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setPasswordRest(userEntity.getPasswordRest());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setContact(userEntity.getContact());
        userDTO.setExpirationDate(userEntity.getExpirationDate());
        userDTO.setDescription(userEntity.getDescription());
        userDTO.setTimeOut(userEntity.getTimeOut());
        userDTO.setDateTimeFormat(userEntity.getDateTimeFormat());
        userDTO.setPrimaryGroupName(userEntity.getPrimaryGroupName());
        userDTO.setLanguage(userEntity.getLanguage());
        userDTO.setOrganisation(userEntity.getOrganisation());
        userDTO.setTimeZone(userEntity.getTimeZone());
        userDTO.setMemo(userEntity.getMemo());

        return userDTO;
    }

    public UserEntity mapToEntity(UserEntity userEntity, UserDTO userDTO) {
        userEntity.setUserName(userDTO.getUsername());
        userEntity.setEmail(userDTO.getUsername());
        userEntity.setPassword(encode(userDTO.getPassword()));
        userEntity.setRole(userDTO.getRole());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setMiddleName(userDTO.getMiddleName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPasswordRest(userDTO.getPasswordRest());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setContact(userDTO.getContact());
        userEntity.setExpirationDate(userDTO.getExpirationDate());
        userEntity.setDescription(userDTO.getDescription());
        userEntity.setTimeOut(userDTO.getTimeOut());
        userEntity.setDateTimeFormat(userDTO.getDateTimeFormat());
        userEntity.setPrimaryGroupName(userDTO.getPrimaryGroupName());
        userEntity.setLanguage(userDTO.getLanguage());
        userEntity.setOrganisation(userDTO.getOrganisation());
        userEntity.setTimeZone(userDTO.getTimeZone());
        userEntity.setMemo(userDTO.getMemo());

        return userEntity;
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
