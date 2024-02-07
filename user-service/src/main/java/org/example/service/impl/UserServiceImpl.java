package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.domain.dto.mapper.UserMapper;
import org.example.domain.dto.request.UserRequestDTO;
import org.example.domain.dto.response.UserResponseForOrder;
import org.example.exceptions.BadAlertException;
import org.example.repository.UserRepository;
import org.example.response.BaseResponse;
import org.example.service.UserService;
import org.example.service.validator.UserCreateValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCreateValidator userCreateValidator;
    private final UserMapper userMapper;

    @Override
    public BaseResponse addUser(UserRequestDTO userRequestDTO) {
//        BaseResponse baseResponse = userCreateValidator.validate(userRequestDTO);
//        if (baseResponse.getData() != null) {
//            return baseResponse;
//        }
        User user = userMapper.toEntity(userRequestDTO);
        userRepository.save(user);
        return BaseResponse.builder().message("User successfully added").statusCode(201).build();
    }

    @Override
    public UserResponseForOrder getUserByUserCode(UUID userCode) {
        Optional<User> user = userRepository.findByUserCode(userCode);
        if (user.isEmpty()){
            throw new BadAlertException("User Not Found","User","Invalid UserCode");
        }
        return userMapper.toOrder(user.get());
    }
}
