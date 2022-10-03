package com.project.gachon.domain.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void test_repo() {
        List<Member> memberList = memberRepository.findByEmail("ydg983@naver.com");

        if (memberList.size() == 0) {
            System.out.println("없는데요");
        } else {
            System.out.println(memberList.toString());
        }

    }
}