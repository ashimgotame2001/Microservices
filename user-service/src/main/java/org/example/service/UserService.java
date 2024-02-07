package org.example.service;


import org.example.domain.dto.request.UserRequestDTO;
import org.example.domain.dto.response.UserResponseForOrder;
import org.example.response.BaseResponse;

import java.util.UUID;

public interface UserService {

    BaseResponse addUser(UserRequestDTO userRequestDTO);

    UserResponseForOrder getUserByUserCode(UUID userCode);

}
