package com.project.gachon.controller;

import com.project.gachon.domain.WeatherEntity;
import com.project.gachon.domain.WeatherEntityRepository;
import com.project.gachon.dto.WeatherDto;
import com.project.gachon.service.WeatherService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WeatherController {
    @Value("${openweather.api.key}")
    private String api_key;
    @Autowired
    WeatherService weatherService;
    @Autowired
    WeatherEntityRepository repo;

    @GetMapping("/comeOnWeather")
    @ResponseBody
    public WeatherDto weather_page(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {

        String url = "https://api.openweathermap.org/data/2.5/weather";
        List<String> weatherData = null;

        try {
            weatherData = weatherService.getWeather(url, api_key, lat, lon); // exception for .getWeather function which can get error while parsing process

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String main = weatherData.get(0);
        String description = weatherData.get(1);

        List<WeatherEntity> entityList;

        entityList = repo.findByMain(main);

        if (entityList.size() == 0) {
            throw new RuntimeException();
        }

        WeatherEntity weatherEntity = entityList.get(0);

        WeatherDto weatherDto = WeatherDto.builder()
                .B(weatherEntity.getB())
                .G(weatherEntity.getG())
                .R(weatherEntity.getR())
                .main(weatherEntity.getMain())
                .description(weatherEntity.getDescription())
                .build();

        return weatherDto;
    }
}