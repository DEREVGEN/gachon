package com.project.gachon.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LogController {

    @GetMapping("/doLogout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        response.setHeader("state", "logout_successful");
        return "/";
    }

    @GetMapping("/session-info")
    @ResponseBody
    public Map<String, String> sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Map<String, String> session_data = new HashMap<>();

        session_data.put("sessionId={}", session.getId());
        session_data.put("maxInactiveInterval={}", String.valueOf(session.getMaxInactiveInterval()));
        session_data.put("creationTime={}", String.valueOf(new Date(session.getCreationTime())));
        session_data.put("lastAccessTjme={}", String.valueOf(new Date(session.getLastAccessedTime())));
        session_data.put("isNew={}", String.valueOf(session.isNew()));

        return session_data;
    }
}
