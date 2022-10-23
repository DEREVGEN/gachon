package com.project.gachon.domain.weather_color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class wcEntityRepositoryTest {

    @Autowired
    wcEntityRepository repo;

    @Test
    void test_repo() {
        repo.save(wcEntity.builder()
                .color("blue")
                .userId("ydg98381@gmail.com")
                .weather("fantastic")
                .build());
    }

    @Test
    void test_repo2() {
        List<wcEntity> wcEntityList = repo.findByWeather("good");

        if (wcEntityList.size() == 0) {
            System.err.println("없습니다.");
        }
        else {
            System.out.println(wcEntityList.get(0).toString());
        }
    }
}