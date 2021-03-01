package com.tungntdo.demo.service.impl;

import com.tungntdo.demo.model.entity.UserEntity;
import com.tungntdo.demo.model.repository.UserRepository;
import com.tungntdo.demo.service.UserService;
import com.tungntdo.demo.shared.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(UserEntity user) {

        // Check email is existed or is not
        UserEntity storedUserDetails = userRepository.findByEmail(user.getEmail());
        if (null != storedUserDetails) {
            throw new RuntimeException("Record already exists");
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
            throw new RuntimeException("No Such Algorithm for hash id");
        }
        userEntity.setUserId(uuid);

        storedUserDetails = userRepository.save(userEntity);

        return storedUserDetails;
    }

    @Override
    public UserEntity getUserByUserId(String userId) {
        UserEntity returnValue = userRepository.findByUserId(userId);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (null == userEntity) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
