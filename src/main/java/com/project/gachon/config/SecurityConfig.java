package com.project.gachon.config;

import com.project.gachon.filter.AuthoritiesLoggingAfterFilter;
import com.project.gachon.filter.FirbaseTokenValidator;
import com.project.gachon.filter.JWTTokenGeneratorFilter;
import com.project.gachon.filter.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@EnableWebSecurity(debug = true) 시큐리티 디버그 설정
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((auth) -> auth
                .antMatchers("/").permitAll()
                        .antMatchers("/weather", "/test").hasAnyAuthority("ROLE_USER", "ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .csrf().disable()
                .addFilterBefore(new FirbaseTokenValidator(), BasicAuthenticationFilter.class) // 파이어베이스 토큰 validation 필터
                //.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class) //jwt 토큰
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
