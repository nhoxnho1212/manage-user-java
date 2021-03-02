package com.tungntdo.demo.security;

import com.tungntdo.demo.config.GlobalConfigs;
import com.tungntdo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationEntryPoint unauthorizedHandler;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurity(UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // We don't need CSRF because out token is invulnerable
                .csrf().disable()
                // All urls must be authenticated (filter for token always fires)
                .authorizeRequests().antMatchers(GlobalConfigs.URL.USER.MAIN)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // Call our errorHandler if authentication/authorization fails
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                // Custom jwt based security filter
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), getApplicationContext()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // Don't create session (REST)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
