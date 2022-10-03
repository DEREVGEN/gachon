package com.project.gachon.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class testController {

    @GetMapping("/test_user")
    @ResponseBody
    public String test_user_page() {
        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection user_role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        System.out.println(user_name);
        System.out.println(user_role.toString());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());

        return "good";
    }
}
