package com.challenge.tenera.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.challenge.tenera.client.IOpenWeatherMapClient;
import com.challenge.tenera.converter.IModelConverter;
import com.challenge.tenera.dao.IWeatherDataDao;
import com.challenge.tenera.model.WeatherData;
import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;
import com.challenge.tenera.service.IWeatherService;

@Service
public class WeatherService implements IWeatherService {

    public static final String CITY_NOT_FOUND_MESSAGE = "City not found";
    @Autowired
    private IOpenWeatherMapClient openWeatherMapClient;
    @Autowired
    private IWeatherDataDao weatherDataDao;
    @Autowired
    private IModelConverter modelConverter;

    @Override
    public WeatherDataDto getCurrentWeatherDataByCityName(String cityName) {
        WeatherData weatherData = openWeatherMapClient.getCurrentWeatherDataByCityName(cityName);
        if (weatherData != null) {
            weatherData.setName(cityName);
            weatherDataDao.save(weatherData);
        }
        return modelConverter.convert(weatherData);
    }

    @Override
    public WeatherDataHistoryDto getWeatherDataHistoryByCityName(String cityName) {
        List<WeatherData> weatherData = weatherDataDao.findTop5ByNameOrderByCreatedAtDesc(cityName);
        if (weatherData == null || weatherData.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, CITY_NOT_FOUND_MESSAGE);
        }
        return modelConverter.convert(weatherData);
    }
}
