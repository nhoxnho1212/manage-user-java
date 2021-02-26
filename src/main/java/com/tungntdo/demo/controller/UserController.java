package com.tungntdo.demo.controller;

import com.tungntdo.demo.config.GlobalConfigs;
import com.tungntdo.demo.config.GlobalConstants;
import com.tungntdo.demo.model.dto.UserDto;
import com.tungntdo.demo.model.request.UserDetailRequestModel;
import com.tungntdo.demo.model.response.UserResponse;
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

        UserDto userDtoRequest = new UserDto();
        BeanUtils.copyProperties(userDetailRequest, userDtoRequest);

        // Create User
        UserDto createdUser =  userService.createUser(userDtoRequest);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

}
