package com.challenge.tenera.client;

import com.challenge.tenera.model.WeatherData;

public interface IOpenWeatherMapClient {

    WeatherData getCurrentWeatherDataByCityName(String cityName);
}