package org.example.domain.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.domain.dto.request.UserRequestDTO;
import org.example.domain.dto.response.UserResponseForOrder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public  User  toEntity (UserRequestDTO userRequestDTO){
        return User.builder()
                .firstName(userRequestDTO.getFirstName())
                .middleName(userRequestDTO.getMiddleName())
                .lastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .role(userRequestDTO.getRole())
                .address(userRequestDTO.getAddress())
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .userCode(UUID.randomUUID())
                .build();
    }

    public UserResponseForOrder toOrder(User user){
       return UserResponseForOrder.builder()
                .firstName(user.getFirstName())
               .middleName(user.getMiddleName())
               .lastName(user.getLastName())
               .email(user.getEmail())
               .phoneNumber(user.getPhoneNumber())
               .userImage(user.getFileStorage())
               .address(user.getAddress())
                .build();
    }
}
