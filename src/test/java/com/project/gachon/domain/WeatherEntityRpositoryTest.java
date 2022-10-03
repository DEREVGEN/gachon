package com.project.gachon.domain;

import com.project.gachon.domain.weather.WeatherEntity;
import com.project.gachon.domain.weather.WeatherEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WeatherEntityRpositoryTest {

    @Autowired
    WeatherEntityRepository repo;

    @Test
    public void test_repo() {
        repo.save(WeatherEntity.builder()
                .R(255)
                .B(255)
                .G(255)
                .main("hi")
                .description("wow")
                .build());

        List<WeatherEntity> weatherEntityList = repo.findByMain("Thunder Storm");

        for(WeatherEntity weatherdata : weatherEntityList) {
            if (weatherdata.getDescription() == null) {
                System.out.println("null 입니당.");
            }
            System.out.println(weatherdata.toString());
        }
    }

    @Test
    public void test_repo2() {
        List<WeatherEntity> weatherEntityList = repo.findByMainAndDescription("Rain", "moderate rain");

        System.out.println(weatherEntityList.get(0).toString());
    }
}