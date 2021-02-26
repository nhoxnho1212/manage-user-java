package com.tungntdo.demo.service.impl;

import com.tungntdo.demo.io.entity.UserEntity;
import com.tungntdo.demo.io.repository.UserRepository;
import com.tungntdo.demo.model.dto.UserDto;
import com.tungntdo.demo.service.UserService;
import com.tungntdo.demo.shared.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        // Check email is existed or is not
        UserEntity storedUserDetails = userRepository.findByEmail(user.getEmail());
        if (null != storedUserDetails) {
            throw new RuntimeException("Record already exists");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        // Hash password
        final String hashedPassword = passwordEncoder.encode(user.getPassword());
        userEntity.setEncryptedPassword(hashedPassword);

        // Set user id use UUID v4
        String uuid;
        try {
            uuid = Util.generateUniqueKeyWithUUIDv4();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No Such Algorithm for hash id");
        }
        userEntity.setUserId(uuid);

        storedUserDetails = userRepository.save(userEntity);

        UserDto returnUser = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnUser);

        return returnUser;
    }
}
