package com.project.gachon.domain.weather;

import com.project.gachon.domain.weather.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherEntityRepository extends JpaRepository<WeatherEntity, Long> {
    List<WeatherEntity> findByMain(String main);
    List<WeatherEntity> findByMainAndDescription(String main, String description);
}
