package com.tungntdo.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tungntdo.demo.config.GlobalConfigs;
import com.tungntdo.demo.config.constant.ErrorMessages;
import com.tungntdo.demo.config.constant.GlobalConstants;
import com.tungntdo.demo.payload.response.ApiResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse response = new ApiResponse(Boolean.FALSE, ErrorMessages.UNAUTHORISED.getErrorMessage());

        OutputStream out = httpServletResponse.getOutputStream();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out, response);
        out.flush();

    }
}
