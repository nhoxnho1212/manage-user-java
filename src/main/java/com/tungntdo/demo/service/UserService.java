package com.tungntdo.demo.service;


import com.tungntdo.demo.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
}
