package com.project.gachon.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="member")
@NoArgsConstructor
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String pwd;

    @Column
    private String role;

    @Builder
    public Member(String email, String pwd, String role) {
        this.email = email;
        this.pwd = pwd;
        this.role = role;
    }
}
