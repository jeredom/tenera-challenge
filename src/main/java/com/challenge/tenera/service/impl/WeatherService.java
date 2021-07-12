package com.challenge.tenera.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.tenera.client.IOpenWeatherMapClient;
import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.service.IWeatherService;

@Service
public class WeatherService implements IWeatherService {

    @Autowired
    private IOpenWeatherMapClient openWeatherMapClient;

    @Override
    public WeatherDataDto getCurrentWeatherDataByCityName(String cityName) {
        return new WeatherDataDto(openWeatherMapClient.getCurrentWeatherDataByCityName(cityName));
    }
}
