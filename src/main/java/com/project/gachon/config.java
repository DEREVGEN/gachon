package com.project.gachon;

import com.project.gachon.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public WeatherService weatherService() {
        return new WeatherService();
    }
}
