package com.challenge.tenera.service;

import com.challenge.tenera.model.WeatherDataDto;

public interface IWeatherService {

    WeatherDataDto getCurrentWeatherDataByCityName(String cityName);
}
