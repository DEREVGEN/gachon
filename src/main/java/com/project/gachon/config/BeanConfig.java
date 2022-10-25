package com.project.gachon.config;

import com.project.gachon.service.LoginService;
import com.project.gachon.service.MovieService;
import com.project.gachon.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public WeatherService weatherService() {
        return new WeatherService();
    }

    @Bean
    public MovieService movieService() {
        return new MovieService();
    }

    @Bean
    public LoginService loginService() {
        return new LoginService();
    }

}
