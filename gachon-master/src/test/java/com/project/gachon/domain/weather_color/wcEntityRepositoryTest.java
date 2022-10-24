package com.project.gachon.domain.weather_color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
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
                        .weatherColor("good.wow")
                        .index(1)
                        .addedData(LocalDateTime.now())
                        .deadlineData(LocalDateTime.now())
                        .userId("ydg983@naver.com")
                .build());
    }
}