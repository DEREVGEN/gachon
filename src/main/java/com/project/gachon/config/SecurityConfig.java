package com.project.gachon.config;

import com.project.gachon.filter.AuthoritiesLoggingAfterFilter;
import com.project.gachon.filter.FirebaseTokenValidator;
import com.project.gachon.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                        .antMatchers("/weather", "/test").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .csrf().disable()
                .addFilterBefore(new FirebaseTokenValidator(), BasicAuthenticationFilter.class) // 파이어베이스 토큰 validation 필터
                //.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class) //jwt 토큰
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .logout().logoutUrl("/doLogout"); // 로그아웃
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // jssession 안씀
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
