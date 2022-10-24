package com.project.gachon.domain.weather_color;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class weatherColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50)
    private String userId;

    @Column(length = 50)
    private String weatherColor;

    @Column
    private int idx;

    @Column
    LocalDateTime createdDate;

    @Column
    LocalDateTime deadDate;

    @Builder
    public weatherColor(String userId, String weatherColor, int idx, LocalDateTime createdDate, LocalDateTime deadDate) {
        this.userId = userId;
        this.weatherColor = weatherColor;
        this.idx = idx;
        this.createdDate = createdDate;
        this.deadDate = deadDate;
    }
}
