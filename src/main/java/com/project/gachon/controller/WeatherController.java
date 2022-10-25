package com.project.gachon.controller;

import com.project.gachon.domain.weather.WeatherEntity;
import com.project.gachon.domain.weather.WeatherEntityRepository;
import com.project.gachon.domain.weather_color.weatherColor;
import com.project.gachon.domain.weather_color.weatherColorRepository;
import com.project.gachon.domain.dto.WeatherDto;
import com.project.gachon.service.WeatherService;
import com.project.gachon.service.aprioriService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WeatherController {

    @Autowired
    WeatherService weatherService;
    @Autowired
    WeatherEntityRepository repo;
    @Autowired
    weatherColorRepository wcRepo;

    @GetMapping("/comeOnWeather")
    @ResponseBody
    public WeatherDto weather_page(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {

        String weatherData = null;

        try {
            weatherData = weatherService.getWeather(lat, lon); // exception for .getWeather function which can get error while parsing process

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String main = weatherData;

        List<WeatherEntity> entityList;

        entityList = repo.findByMain(main);

        if (entityList.size() == 0) {
            throw new RuntimeException();
        }

        WeatherEntity weatherEntity = entityList.get(0);

        WeatherDto weatherDto = WeatherDto.builder()
                .color(weatherEntity.getColor())
                .main(weatherEntity.getMain())
                .build();

        return weatherDto;
    }

    @GetMapping("anotherplz")
    @ResponseBody
    public String another_color(@RequestParam(value="lat",required = false) double lat, @RequestParam(value = "lon",required = false) double lon,
                                @RequestParam(value="weather", required = false) String weather, @RequestParam(value="color") String color) {
        List<weatherColor> weatherColors = wcRepo.findByUserIdByLast(SecurityContextHolder.getContext().getAuthentication().getName());
        int idx;

        if (LocalDateTime.now().isBefore(weatherColors.get(0).getDeadDate())) { // if the dead date of last record is not passed to now date
            wcRepo.save(weatherColor.builder()
                    .idx(weatherColors.get(0).getIdx())
                    .userId(SecurityContextHolder.getContext().getAuthentication().getName())
                    .weatherColor(weather+'.'+color)
                    .createdDate(LocalDateTime.now())
                    .deadDate(weatherColors.get(0).getDeadDate())
                    .build());
        } else { // now date is passed to dead date of last record
            wcRepo.save(weatherColor.builder()
                    .idx(weatherColors.get(0).getIdx()+1)
                    .userId(SecurityContextHolder.getContext().getAuthentication().getName())
                    .weatherColor(weather+'.'+color)
                    .createdDate(LocalDateTime.now())
                    .deadDate(LocalDateTime.now().plusDays(5))
                    .build());
        }

        return "good";
    }

    @GetMapping("giveMeColor")
    @ResponseBody
    public String give_me_color(@RequestParam(value="lat",required = false) double lat, @RequestParam(value = "lon",required = false) double lon) throws ParseException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<weatherColor> weatherColors = wcRepo.findByUserIdByLast(userId);

        if (weatherColors.size() == 0) {
            throw new RuntimeException();
        }

        int last_idx = weatherColors.get(0).getIdx();

        weatherColors = wcRepo.findByIdxGreaterThan(last_idx - 5);

        ArrayList[] item_list = new ArrayList[5];
        for (int i = 0; i < item_list.length; i++) {
            item_list[i] = new ArrayList<String>();
        }

        for (int i = 0; i < weatherColors.size(); i++) {
            item_list[weatherColors.get(i).getIdx() % 5].add(weatherColors.get(i).getWeatherColor());
        }

        for (int i = 0; i < item_list.length; i++) {
            System.out.print(i+".");
            for (int j = 0; j < item_list[i].size(); j++) {
                System.out.print(" " + item_list[i].get(j));
            }
            System.out.println("");
        }

        Map<String, String> results = null;

        aprioriService service = new aprioriService(item_list);
        try {
            results = service.apriori();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String weather = weatherService.getWeather(lat, lon); // 현재 날씨

        for (String key : results.keySet()) {
            if (key == weather) { // found matched weather-color
                return results.get(key);
            }
        }

        //not found matched weather-color
        List<WeatherEntity> entityList = repo.findByMain(weather);
        return entityList.get(0).getMain();
    }
}
