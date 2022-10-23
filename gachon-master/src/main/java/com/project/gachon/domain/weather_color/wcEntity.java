package com.project.gachon.domain.weather_color;

import com.google.firebase.database.annotations.NotNull;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="wc")
@ToString
public class wcEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50)
    @NotNull
    private String userId;

    @Column(length = 50)
    @NotNull
    private String weather;

    @Column(length = 50)
    @NotNull
    private String color;

    @Builder
    public wcEntity(String userId, String weather, String color) {
        this.userId = userId;
        this.weather = weather;
        this.color = color;
    }
}
