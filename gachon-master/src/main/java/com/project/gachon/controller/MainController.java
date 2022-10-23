package com.project.gachon.controller;


import com.project.gachon.domain.member.Member;
import com.project.gachon.domain.member.MemberRepository;
import com.project.gachon.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main_page() {
        return "mainPage";
    }

    @GetMapping("/weather")
    public String weather_page() {
        return "weatherPage";
    }

    @GetMapping("/register")
    public String register_page() {
        return "registerPage";
    }

    @GetMapping("/movie")
    public String movie_page() {
        return "moviePage";
    }

    @PostMapping("/register")
    public String register_member(MemberDto memberDto) {
        List<Member> memberList = memberRepository.findByEmail(memberDto.getEmail());

        if (memberList.size() >= 1) {
            System.err.println("해당 사용자는 이미 있습니다.");
            throw new RuntimeException();
        }

        memberRepository.save(Member.builder()
                .pwd(passwordEncoder.encode(memberDto.getPwd()))
                .email(memberDto.getEmail())
                .role("ROLE_USER")
                .build());
        return "mainPage";
    }
}
