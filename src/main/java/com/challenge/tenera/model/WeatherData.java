package com.challenge.tenera.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    public String id;
    public String name;
    public Double temperature;
    public Integer pressure;
    public String weatherCondition;

    public WeatherData() {
        // For Jackson
    }

    public WeatherData(String id, String name, Double temperature, Integer pressure, String weatherCondition) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.pressure = pressure;
        this.weatherCondition = weatherCondition;
    }

    @JsonProperty("main")
    private void unpackTemperaturAndPressureFromNested(Map<String, Object> main) {
        this.temperature = (Double) main.get("temp");
        this.pressure = (Integer) main.get("pressure");
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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

}
