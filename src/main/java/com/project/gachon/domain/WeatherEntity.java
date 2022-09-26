package com.project.gachon.domain;

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

    @Column
    @NonNull
    private String main;

    @Column
    private String description;

    @Column
    @NonNull
    private int R, G, B;

    @Builder
    public WeatherEntity(String main, String description, int R, int G, int B) {
        this.main = main;
        this.description = description;
        this.R = R;
        this.G = G;
        this.B = B;
    }
}
