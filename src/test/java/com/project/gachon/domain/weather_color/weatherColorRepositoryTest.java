package com.project.gachon.domain.weather_color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
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
}