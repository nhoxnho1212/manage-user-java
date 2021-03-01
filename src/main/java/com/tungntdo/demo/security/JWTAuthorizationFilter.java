package com.tungntdo.demo.security;

import com.tungntdo.demo.config.GlobalConfigs;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
       final String header = request.getHeader(GlobalConfigs.JWT_SECURITY.ACCESS_TOKEN.HEADER_STRING);

       if (null == header || !header.startsWith(GlobalConfigs.JWT_SECURITY.ACCESS_TOKEN.TOKEN_PREFIX)) {
           chain.doFilter(request, response);
           return;
       }

       UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
       SecurityContextHolder.getContext().setAuthentication(authenticationToken);
       chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(GlobalConfigs.JWT_SECURITY.ACCESS_TOKEN.HEADER_STRING);

        if (null != token) {
            token = token.replace(GlobalConfigs.JWT_SECURITY.ACCESS_TOKEN.TOKEN_PREFIX, "");

            String tokenId = Jwts.parser()
                    .setSigningKey(GlobalConfigs.JWT_SECURITY.getTokenSecret())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (null != tokenId) {
                return new UsernamePasswordAuthenticationToken(tokenId, null, new ArrayList<>());
            }

        }
        return null;
    }
}
