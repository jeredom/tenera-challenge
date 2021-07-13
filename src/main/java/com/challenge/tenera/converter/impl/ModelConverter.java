package com.challenge.tenera.converter.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge.tenera.converter.IModelConverter;
import com.challenge.tenera.model.WeatherData;
import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;

@Service
public class ModelConverter implements IModelConverter {

    @Override
    public WeatherDataDto convert(WeatherData weatherData) {
        WeatherDataDto weatherDataDto = new WeatherDataDto();

        if (weatherData != null) {
            weatherDataDto.setPressure(weatherData.getPressure());
            weatherDataDto.setTemperature(weatherData.getTemperature());
            weatherDataDto.setUmbrella(weatherData.getWeatherCondition() != null
                    ? weatherData.getWeatherCondition().toLowerCase().matches("thunderstorm|drizzle|rain")
                    : null);
        }
        return weatherDataDto;
    }

    @Override
    public WeatherDataHistoryDto convert(List<WeatherData> history) {
        WeatherDataHistoryDto weatherDataHistoryDto = null;

        if (history != null && !history.isEmpty()) {
            Double accumulatedTemperature = 0d;
            Double accumulatedPressure = 0d;
            List<WeatherDataDto> dtos = new LinkedList<>();

            for (WeatherData data : history) {
                dtos.add(convert(data));
                accumulatedTemperature += data.getTemperature() != null ? data.getTemperature() : 0;
                accumulatedPressure += data.getPressure() != null ? data.getPressure() : 0;
            }

            Integer historySize = history.size();
            weatherDataHistoryDto = new WeatherDataHistoryDto(accumulatedTemperature / historySize,
                    accumulatedPressure / historySize, dtos);
        }
        return weatherDataHistoryDto;
    }

}
