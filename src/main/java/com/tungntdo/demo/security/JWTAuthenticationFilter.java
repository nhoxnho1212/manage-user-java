package com.tungntdo.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tungntdo.demo.config.GlobalConfigs;
import com.tungntdo.demo.config.GlobalConstants;
import com.tungntdo.demo.model.entity.TokenEntity;
import com.tungntdo.demo.payload.request.UserLoginRequestModel;
import com.tungntdo.demo.payload.response.UserLoginResponse;
import com.tungntdo.demo.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private TokenService tokenService;

    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext applicationContext) {
        this.authenticationManager = authenticationManager;
        this.tokenService = applicationContext.getBean(TokenService.class);
        setFilterProcessesUrl(GlobalConfigs.URL.USER.LOGIN);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // Parse JSON content to Java object
            UserLoginRequestModel userLoginRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequestModel.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword(),
                        new ArrayList<>()
                )
            );
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String userEmail = ((User) authResult.getPrincipal()).getUsername();

        TokenEntity tokenEntity = tokenService.createTokenByUser(userEmail, GlobalConstants.TOKEN_TYPE.ACCESS_TOKEN);

        String accessToken = Jwts.builder()
                .setSubject(tokenEntity.getTokenId())
                .setExpiration(tokenEntity.getExpiresAt())
                .signWith(SignatureAlgorithm.HS256, GlobalConfigs.JWT_SECURITY.getTokenSecret())
                .compact();

//        response.addHeader(GlobalConfigs.JWT_SECURITY.ACCESS_TOKEN.HEADER_STRING,
//                GlobalConfigs.JWT_SECURITY.ACCESS_TOKEN.TOKEN_PREFIX + accessToken);

        UserLoginResponse returnValue = new UserLoginResponse();
        returnValue.setAccessToken(accessToken);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(returnValue.toString());
    }
}
