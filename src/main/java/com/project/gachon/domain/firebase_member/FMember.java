package com.project.gachon.domain.firebase_member;

import com.google.firebase.database.annotations.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class FMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50)
    @NotNull
    private String email;

    @Column
    private String role;

    @Builder
    public FMember(String email, String role) {
        this.email = email;
        this.role = role;
    }
}
