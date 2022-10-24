package com.project.gachon.domain.weather_color;

import com.google.firebase.database.annotations.NotNull;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String weatherColor;

    @Column
    private LocalDateTime addedData;

    @Column
    private LocalDateTime deadlineData;

    @Column
    private int index;

    @Builder
    public wcEntity(String userId, String weatherColor, LocalDateTime addedData, LocalDateTime deadlineData, int index) {
        this.userId = userId;
        this.weatherColor = weatherColor;
        this.addedData = addedData;
        this.deadlineData = deadlineData;
        this.index = index;
    }
}
