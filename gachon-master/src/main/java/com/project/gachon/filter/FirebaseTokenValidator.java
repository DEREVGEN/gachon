package com.project.gachon.filter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.project.gachon.constants.SecurityConstants;
import com.project.gachon.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirebaseTokenValidator extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        FirebaseToken decodedToken;

        String ft = request.getHeader(SecurityConstants.JWT_HEADER);

        if (ft != null && ft.startsWith("Bearer ")) {
            String idToken = ft.substring(7);

            // test 코드
            if (idToken.equals("1234")) {
                 List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
                 roles.add(new SimpleGrantedAuthority("ROLE_USER"));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user1", null, roles);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                response.setHeader(SecurityConstants.JWT_HEADER, "token here");
            }
            else {
                // 본 코드 - firebase 토큰 검증
                try {
                    //파이어베이스 사용자 정보
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                    String email = decodedToken.getEmail();

                    //권한
                    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
                    roles.add(new SimpleGrantedAuthority("ROLE_USER"));

                    //서버 로그인
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, roles);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } catch (FirebaseAuthException e) {
                    setUnauthorizedResponse(response, "INVALID_TOKEN");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setUnauthorizedResponse(HttpServletResponse response, String code) throws IOException {
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"code\":\""+code+"\"}");
    }
}
