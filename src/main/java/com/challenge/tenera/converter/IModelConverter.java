package com.challenge.tenera.converter;

import java.util.List;

import com.challenge.tenera.model.WeatherData;
import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;

public interface IModelConverter {

    WeatherDataDto convert(WeatherData weatherData);

    WeatherDataHistoryDto convert(List<WeatherData> history);
}
