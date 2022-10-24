package com.project.gachon.filter;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuthoritiesLoggingAfterFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            System.out.println(LocalDateTime.now() + " - " + "User: " + authentication.getName() + " is successfully authenticated and has the authorities" +
                    authentication.getAuthorities().toString() + " - access to " + request.getRequestURL());
        } else {
            throw new BadCredentialsException("you are not my member!!");
        }

        filterChain.doFilter(request, response);
    }
}
