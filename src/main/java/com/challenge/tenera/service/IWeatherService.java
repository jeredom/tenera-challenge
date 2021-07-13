package com.challenge.tenera.service;

import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;

public interface IWeatherService {

    WeatherDataDto getCurrentWeatherDataByCityName(String cityName);

    WeatherDataHistoryDto getWeatherDataHistoryByCityName(String cityName);
}
