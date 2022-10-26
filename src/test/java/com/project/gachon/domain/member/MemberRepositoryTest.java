package com.project.gachon.domain.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test_repo() {
        List<Member> memberList = memberRepository.findByEmail("ydg983@naver.com");

        if (memberList.size() == 0) {
            System.out.println("없는데요");
        } else {
            System.out.println(memberList.toString());
        }

    }

    @Test
    public void register_repo() {
        memberRepository.save(Member.builder()
                .email("ydg983@naver.com")
                .pwd(passwordEncoder.encode("1234"))
                .role("ROLE_USER")
                .build());
    }

    @Test
    public void register2_repo() {
        memberRepository.save(Member.builder()
                .email("ydg9838@naver.com")
                .pwd(passwordEncoder.encode("1234"))
                .role("ROLE_USER")
                .build());
    }
}