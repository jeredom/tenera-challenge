package com.challenge.tenera.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    public String id;
    public String name;
    public Long temperature;
    public Long pressure;
    public String weatherCondition;

    public WeatherData() {
        // For Jackson
    }

    public WeatherData(String id, String name, Long temperature, Long pressure, String weatherCondition) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.pressure = pressure;
        this.weatherCondition = weatherCondition;
    }

    @JsonProperty("main")
    private void unpackTemperaturAndPressureFromNested(Map<String, Object> main) {
        this.temperature = (Long) main.get("temp");
        this.pressure = (Long) main.get("pressure");
    }

    @JsonProperty("weather")
    private void unpackWeatherConditionFromNested(List<Map<String, Object>> weather) {
        this.weatherCondition = (String) weather.get(0).get("main");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }

    public Long getPressure() {
        return pressure;
    }

    public void setPressure(Long pressure) {
        this.pressure = pressure;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

}
