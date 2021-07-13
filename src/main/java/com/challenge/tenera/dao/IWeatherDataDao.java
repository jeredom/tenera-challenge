package com.challenge.tenera.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.tenera.model.WeatherData;

public interface IWeatherDataDao extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findTop5ByNameOrderByCreatedAtDesc(String name);
}
