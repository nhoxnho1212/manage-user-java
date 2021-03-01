package com.tungntdo.demo.service;

import com.tungntdo.demo.config.constant.GlobalConstants;
import com.tungntdo.demo.model.entity.TokenEntity;

public interface TokenService {
    TokenEntity createTokenByUser(String email, GlobalConstants.TOKEN_TYPE tokenType);
}
