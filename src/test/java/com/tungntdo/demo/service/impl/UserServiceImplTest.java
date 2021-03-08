package com.tungntdo.demo.service.impl;

import com.tungntdo.demo.exception.UserServiceException;
import com.tungntdo.demo.model.entity.UserEntity;
import com.tungntdo.demo.model.repository.UserRepository;
import com.tungntdo.demo.service.UserService;
import com.tungntdo.demo.shared.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    UserEntity userEntity;

    final String userUUID = "xxxxxxxx-xxxx-Bxxx-Axxx-xxxxxxxxxxxx";
    final String userFirstname = "firstnameTEST";
    final String userLastname = "lastnameTEST";
    final String userEmail = "email@testmail.comTEST";
    final String userPassword = "pwdTEST";

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserId(userUUID);
        userEntity.setFirstName(userFirstname);
        userEntity.setLastName(userLastname);
        userEntity.setPassword(userPassword);
        userEntity.setEmail(userEmail);
    }

    @Test
    void testGetUserByUserId() {
        when( userRepository.findByUserId(anyString())).thenReturn(userEntity);

        UserEntity user = userService.getUserByUserId(userUUID);

        assertNotNull(user);
        assertEquals(userFirstname, user.getFirstName());
    }

    @Test
    void testGetUserByUserId_UserIdNotFoundException() {

        when( userRepository.findByUserId(anyString())).thenReturn(null);

        assertThrows(UserServiceException.class,
                () -> {
                    userService.getUserByUserId(userUUID);
                }
                );
    }

    @Test
    void testCreateUser_CreateUserServiceException() {
        when( userRepository.findByEmail(anyString())).thenReturn(userEntity);

        assertThrows(UserServiceException.class,
                () -> {
                    userService.createUser(userEntity);
                }
                );
    }


    @Test
    void testCreateUser() throws NoSuchAlgorithmException {
        when( userRepository.findByEmail(anyString())).thenReturn(null);
        when( passwordEncoder.encode(anyString())).thenReturn(userPassword);
        try (MockedStatic mocked = mockStatic(Util.class)) {
            mocked.when(Util::generateUniqueKeyWithUUIDv4).thenReturn(userUUID);
        }
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity storedUser = userService.createUser(userEntity);

        assertNotNull(storedUser);
        assertEquals(userEntity.getFirstName(),storedUser.getFirstName());
        assertEquals(userEntity.getLastName(), storedUser.getLastName());
        assertNotNull(storedUser.getUserId());
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(any(UserEntity.class));

    }
}