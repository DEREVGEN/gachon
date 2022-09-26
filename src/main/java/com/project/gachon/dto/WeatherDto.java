package com.project.gachon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDto {

    private String main;
    private String description;
    private int R, G, B;

    @Builder
    public WeatherDto(String main, String description, int R, int G, int B) {
        this.main = main;
        this.description = description;
        this.R = R;
        this.G = G;
        this.B = B;
    }
}
