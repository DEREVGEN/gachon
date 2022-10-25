package com.project.gachon.domain.weather_color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface weatherColorRepository extends JpaRepository<weatherColor, Long> {
    List<weatherColor> findByUserId(String userId);

    @Query(value = "select * from weather_color i where i.user_id like %:userId% order by i.idx desc limit 1", nativeQuery = true)
    List<weatherColor> findByUserIdByLast(@Param("userId") String userId);

    List<weatherColor> findByIdxGreaterThan(int idx);
}
