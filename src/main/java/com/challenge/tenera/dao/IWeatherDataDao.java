package com.challenge.tenera.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.tenera.model.WeatherData;

public interface IWeatherDataDao extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findTop5ByNameOrderByCreatedAtDesc(String name);

    @Query("SELECT w FROM WeatherData w WHERE w.name LIKE :cityName ORDER BY max(createdAt) desc")
    WeatherData findTop5ByNameOrderBy(@Param("cityName") String name);
}
