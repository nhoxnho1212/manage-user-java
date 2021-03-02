package com.tungntdo.demo.service.impl;

import com.tungntdo.demo.config.constant.ErrorMessages;
import com.tungntdo.demo.config.constant.ResponseMessages;
import com.tungntdo.demo.exception.UserServiceException;
import com.tungntdo.demo.model.entity.UserEntity;
import com.tungntdo.demo.model.repository.UserRepository;
import com.tungntdo.demo.payload.response.ApiResponse;
import com.tungntdo.demo.service.UserService;
import com.tungntdo.demo.shared.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(UserEntity user) {

        // Check email is existed or is not
        UserEntity storedUserDetails = userRepository.findByEmail(user.getEmail());
        if (null != storedUserDetails) {
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_ERROR.getErrorMessage());
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        // Hash password
        final String hashedPassword = passwordEncoder.encode(user.getPassword());
        userEntity.setPassword(hashedPassword);

        // Set user id use UUID v4
        String uuid;
        try {
            uuid = Util.generateUniqueKeyWithUUIDv4();
        } catch (NoSuchAlgorithmException e) {
            logger.error(ErrorMessages.NO_SUCH_ALGORITHM_FOR_HASH_ID.getErrorMessage());
            throw new RuntimeException(ErrorMessages.INTERNAL_SERVER_ERROR.getErrorMessage());
        }
        userEntity.setUserId(uuid);

        storedUserDetails = userRepository.save(userEntity);

        return storedUserDetails;
    }

    @Override
    public UserEntity getUserByUserId(String userId) {
        UserEntity returnValue = userRepository.findByUserId(userId);

        if (null == returnValue) {
            throw new UserServiceException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
        }

        return returnValue;
    }

    @Override
    public UserEntity updateUser(String userId, UserEntity userDetailsRequest) {

        UserEntity user = getUserByUserId(userId);
        if (null != userDetailsRequest.getFirstName()) {
            user.setFirstName(userDetailsRequest.getFirstName());
        }
        if (null != userDetailsRequest.getLastName()) {
            user.setLastName(userDetailsRequest.getLastName());
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public ApiResponse deleteUser(String userId) {

        UserEntity user = getUserByUserId(userId);

        userRepository.delete(user);

        return new ApiResponse(Boolean.TRUE, ResponseMessages.USER.DELETE_SUCCESS.name());
    }

    @Override
    public List<UserEntity> getUsers(Integer page, Integer limit) {

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (null == userEntity) {
            throw new UserServiceException(ErrorMessages.USER_NOT_FOUND.getErrorMessage());
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
