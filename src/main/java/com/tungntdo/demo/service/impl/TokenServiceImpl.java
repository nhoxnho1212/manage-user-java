package com.tungntdo.demo.service.impl;

import com.tungntdo.demo.config.GlobalConfigs;
import com.tungntdo.demo.config.GlobalConstants;
import com.tungntdo.demo.model.entity.TokenEntity;
import com.tungntdo.demo.model.entity.TokenTypeEntity;
import com.tungntdo.demo.model.entity.UserEntity;
import com.tungntdo.demo.model.repository.TokenRepository;
import com.tungntdo.demo.model.repository.TokenTypeRepository;
import com.tungntdo.demo.model.repository.UserRepository;
import com.tungntdo.demo.service.TokenService;
import com.tungntdo.demo.shared.Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    TokenTypeRepository tokenTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public TokenEntity createTokenByUser(String email, GlobalConstants.TOKEN_TYPE tokenType) {
        TokenEntity storedToken = new TokenEntity();
        TokenTypeEntity tokenTypeEntity = tokenTypeRepository.findTokenTypeEntityByTypeName(tokenType.name());
        UserEntity user = userRepository.findByEmail(email);

        String tokenId = "";
        try {
            tokenId = Util.generateUniqueKeyWithUUIDv4();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        long expirationTime = 0;

        switch (tokenType) {
            case EMAIL_TOKEN:
                break;
            case REFRESH_TOKEN:
                break;
            case ACCESS_TOKEN:
                expirationTime = System.currentTimeMillis() + GlobalConfigs.JWT_SECURITY.ACCESS_TOKEN.EXPIRATION_TIME;
                break;
        }

        storedToken.setUser(user);
        storedToken.setExpiresAt(new Timestamp(expirationTime));
        storedToken.setTokenType(tokenTypeEntity);
        storedToken.setRevoked(true);
        storedToken.setTokenId(tokenId);

        tokenRepository.save(storedToken);

        return storedToken;
    }
}
