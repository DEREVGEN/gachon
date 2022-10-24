package com.project.gachon.domain.weather;

import com.google.firebase.database.annotations.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="weather")
@ToString
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 50)
    @NotNull
    private String main;

    @Column(length = 50)
    @NotNull
    private String color;

    @Builder
    public WeatherEntity(String main, String color) {
        this.main = main;
        this.color = color;
    }
}
