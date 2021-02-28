package com.tungntdo.demo.service;

import com.tungntdo.demo.config.GlobalConstants;
import com.tungntdo.demo.model.entity.TokenEntity;
import com.tungntdo.demo.model.entity.UserEntity;

public interface TokenService {
    TokenEntity createTokenByUser(String email, GlobalConstants.TOKEN_TYPE tokenType);
}
