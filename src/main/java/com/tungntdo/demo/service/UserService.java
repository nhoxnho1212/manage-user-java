package com.tungntdo.demo.service;


import com.tungntdo.demo.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity createUser(UserEntity user);
}
