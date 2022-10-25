package com.project.gachon.domain.firebase_member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FMemberRepository extends JpaRepository<FMember, Long> {
    Optional<FMember> findByEmail(String email);
}
