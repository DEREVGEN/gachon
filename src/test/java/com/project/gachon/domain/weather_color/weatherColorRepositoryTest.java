package com.project.gachon.domain.weather_color;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class weatherColorRepositoryTest {

    @Autowired
    weatherColorRepository repo;

    @Test
    void test_repo() {

        for (int i = 2; i < 10; i++) {
            repo.save(weatherColor.builder()
                    .idx(i)
                    .userId("ydg983" + i + "@naver.com")
                    .weatherColor("red")
                    .createdDate(LocalDateTime.now())
                    .deadDate(LocalDateTime.now())
                    .build());
        }
    }

    @Test
    void test2_repo() {
        List<weatherColor> weatherColors = repo.findByUserIdByLast("ydg983@naver.com");

        if (weatherColors.size() == 0) {
            System.err.println("없습니다.");
        } else {
            System.out.println(weatherColors.get(0).toString());
        }
    }

    @Test
    @DisplayName("test - weather-color to ArrayList")
    void test3_repo() {
        ArrayList[] stringInput = new ArrayList[5];
        for (int i = 0; i < stringInput.length; i++)
            stringInput[i] = new ArrayList<String>();
        stringInput[0].add("Rain.blue");
        stringInput[0].add("Rain.green");
        stringInput[0].add("Drizzle.yellow");
        stringInput[0].add("Rain.purple");
        stringInput[0].add("Rain.blue");
        stringInput[1].add("Thunderstorm.blue");
        stringInput[1].add("Rain.green");
        stringInput[1].add("Thunderstorm.purple");
        stringInput[2].add("Rain.blue");
        stringInput[2].add("Drizzle.yellow");
        stringInput[2].add("Rain.purple");
        stringInput[3].add("Drizzle.blue");
        stringInput[3].add("Atmosphere.white");
        stringInput[3].add("Clouds.orange");
        stringInput[3].add("Clouds.purple");
        stringInput[4].add("Clouds.orange");
        stringInput[4].add("Clear.white");
        stringInput[4].add("Clear.green");


        for (int i = 0; i < stringInput.length; i++) {
            for (int j = 0; j < stringInput[i].size(); j++) {
                repo.save(weatherColor.builder()
                        .weatherColor((String) stringInput[i].get(j))
                        .idx(i)
                        .createdDate(LocalDateTime.now())
                        .deadDate(LocalDateTime.now())
                        .userId("ydg983@naver.com")
                        .build());
            }
        }
    }
}