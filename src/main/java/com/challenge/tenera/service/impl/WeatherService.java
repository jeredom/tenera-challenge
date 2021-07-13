package com.challenge.tenera.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.tenera.client.IOpenWeatherMapClient;
import com.challenge.tenera.dao.impl.WeatherDataDao;
import com.challenge.tenera.model.WeatherData;
import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.service.IWeatherService;

@Service
public class WeatherService implements IWeatherService {

    @Autowired
    private IOpenWeatherMapClient openWeatherMapClient;
    @Autowired
    private WeatherDataDao weatherDataDao;

    @Override
    public WeatherDataDto getCurrentWeatherDataByCityName(String cityName) {
        WeatherData weatherData = openWeatherMapClient.getCurrentWeatherDataByCityName(cityName);

        if (weatherData != null) {
            weatherData.setName(cityName);
            weatherDataDao.save(weatherData);
        }

        List<WeatherData> data = weatherDataDao.findTop5ByNameOrderByCreatedAtDesc(cityName);

        return weatherData != null ? new WeatherDataDto(weatherData) : null;
    }
}
