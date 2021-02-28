package com.tungntdo.demo.controller;

import com.tungntdo.demo.config.GlobalConfigs;
import com.tungntdo.demo.model.entity.UserEntity;
import com.tungntdo.demo.payload.request.UserDetailRequestModel;
import com.tungntdo.demo.payload.response.UserResponse;
import com.tungntdo.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = GlobalConfigs.URL.USER.MAIN,
        method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }
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

//    @PostMapping(path = GlobalConfigs.URL.USER.LOGIN)
//    public UserLoginResponse loginUser(@Valid @RequestBody UserDetailRequestModel userDetailRequest) {
//        UserLoginResponse returnValue = new UserLoginResponse();
//
//        return returnValue;
//    }

}
