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
                .main("hi")
                .color("white")
                .build());
    }

    @Test
    public void test_repo2() {
        List<WeatherEntity> weatherEntityList = repo.findByMain("Rain");

        System.out.println(weatherEntityList.get(0).toString());
    }
}