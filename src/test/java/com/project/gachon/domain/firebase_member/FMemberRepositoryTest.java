package com.project.gachon.domain.firebase_member;

import com.project.gachon.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FMemberRepositoryTest {

    @Autowired
    FMemberRepository repo;

    @Autowired
    LoginService loginService;

    @Test
    void FmemRepoTest() {
        try {
            loginService.UserRegister("ydg98381@gmail.com");
        } catch (Exception e) {
            System.err.println("이미 존재하는 사용자입니다.");
        }

        //Optional<FMember> fMember = repo.findByEmail("ydg983@naver.com");
    }
}