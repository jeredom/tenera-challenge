package com.challenge.tenera.model;

import java.util.List;

public class WeatherDataHistoryDto {

    private Double averageTemperature;
    private Double averagePressure;
    private List<WeatherDataDto> history;

    public WeatherDataHistoryDto() {
    }

    public WeatherDataHistoryDto(Double averageTemperature, Double averagePressure, List<WeatherDataDto> history) {
        this.averageTemperature = averageTemperature;
        this.averagePressure = averagePressure;
        this.history = history;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public Double getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(Double averagePressure) {
        this.averagePressure = averagePressure;
    }

    public List<WeatherDataDto> getHistory() {
        return history;
    }

    public void setHistory(List<WeatherDataDto> history) {
        this.history = history;
    }
}
