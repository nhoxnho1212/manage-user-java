package com.tungntdo.demo.controller;

import com.tungntdo.demo.config.GlobalConfigs;
import com.tungntdo.demo.model.entity.UserEntity;
import com.tungntdo.demo.payload.request.UserDetailRequestModel;
import com.tungntdo.demo.payload.request.UserUpdateRequestModel;
import com.tungntdo.demo.payload.response.ApiResponse;
import com.tungntdo.demo.payload.response.UserResponse;
import com.tungntdo.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = GlobalConfigs.URL.USER.MAIN
)
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserDetailRequestModel userDetailRequest) {

        UserResponse returnValue = new UserResponse();

        UserEntity userRequest = new UserEntity();
        BeanUtils.copyProperties(userDetailRequest, userRequest);

        // Create User
        UserEntity createdUser =  userService.createUser(userRequest);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @GetMapping(
        path = "/{userId}"
    )
    public UserResponse getUser(@PathVariable String userId) {

        UserResponse returnValue = new UserResponse();

        UserEntity userEntity = userService.getUserByUserId(userId);

        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @PutMapping(
            path = "/{userId}"
    )
    public UserResponse udpateUser(@PathVariable String userId, @Valid @RequestBody UserUpdateRequestModel userDetails) {
        UserEntity userRequest = new UserEntity();
        BeanUtils.copyProperties(userDetails, userRequest);

        UserEntity updatedValue = userService.updateUser(userId, userRequest);

        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(updatedValue, returnValue);

        return returnValue;

    }

    @DeleteMapping(
            path = "/{userId}"
    )
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        ApiResponse returnValue = userService.deleteUser(userId);

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

}

