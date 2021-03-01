package com.tungntdo.demo.service;


import com.tungntdo.demo.model.entity.UserEntity;
import com.tungntdo.demo.payload.response.ApiResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity createUser(UserEntity user);
    UserEntity getUserByUserId(String userId);
    UserEntity updateUser(String userId, UserEntity userDetailsRequest);
    ApiResponse deleteUser(String userId);
}
