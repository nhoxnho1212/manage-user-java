package com.tungntdo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppPropertiesConfig {

    @Bean(name="AppProperties")
    public AppProperties getAppProperties() {
        return new AppProperties();
    }
}
