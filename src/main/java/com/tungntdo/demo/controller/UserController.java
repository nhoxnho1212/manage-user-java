package com.tungntdo.demo.controller;

import com.tungntdo.demo.config.Constants;
import com.tungntdo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = Constants.URL.USER.MAIN,
        method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }
)
public class UserController {

    @Autowired
    UserService userService;
}
