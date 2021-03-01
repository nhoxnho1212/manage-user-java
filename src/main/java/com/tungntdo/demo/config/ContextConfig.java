package com.tungntdo.demo.config;

import com.tungntdo.demo.SpringApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }
}
