package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.domain.dto.request.UserRequestDTO;
import org.example.domain.dto.response.UserResponseForOrder;
import org.example.response.BaseResponse;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        BaseResponse baseResponse = userService.addUser(userRequestDTO);
        return new ResponseEntity<>(baseResponse, HttpStatusCode.valueOf(baseResponse.getStatusCode()));
    }

    @GetMapping("/{userCode}")
   public ResponseEntity<UserResponseForOrder>  getUserByUserCode(@PathVariable UUID userCode){
        UserResponseForOrder baseResponse = userService.getUserByUserCode(userCode);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
