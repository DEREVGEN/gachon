package com.project.gachon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDto {

    private String main;
    private String color;

    @Builder
    public WeatherDto(String main, String color) {
        this.main = main;
        this.color = color;
    }
}
