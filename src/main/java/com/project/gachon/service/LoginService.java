package com.project.gachon.service;

import com.project.gachon.domain.firebase_member.FMember;
import com.project.gachon.domain.firebase_member.FMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class LoginService {

    @Autowired
    FMemberRepository repo;

    public FMember UserRegister(String email) {
        Optional<FMember> fMember = repo.findByEmail(email);

        if (!fMember.isPresent()) {
            repo.save(FMember.builder()
                    .email(email)
                    .role("ROLE_USER")
                    .build());
        }

        return fMember.get();
    }
}
