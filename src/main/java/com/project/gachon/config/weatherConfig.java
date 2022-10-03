package com.project.gachon.config;

import com.project.gachon.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class weatherConfig {

    @Bean
    public WeatherService weatherService() {
        return new WeatherService();
    }
}
