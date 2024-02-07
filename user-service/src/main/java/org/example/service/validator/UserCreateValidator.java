package org.example.service.validator;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.request.UserRequestDTO;
import org.example.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateValidator {

    public BaseResponse validate(UserRequestDTO userRequestDTO){
        return  null;
    }


}
