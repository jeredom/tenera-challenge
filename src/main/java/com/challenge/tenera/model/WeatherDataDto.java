package com.challenge.tenera.model;

public class WeatherDataDto {

    private Double temperature;
    private Integer pressure;
    private boolean umbrella;

    public WeatherDataDto(WeatherData weatherData) {
        this.temperature = weatherData.getTemperature();
        this.pressure = weatherData.getPressure();
        this.umbrella = weatherData.getWeatherCondition().toLowerCase().matches("thunderstorm|drizzle|rain");
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

    public boolean isUmbrella() {
        return umbrella;
    }

    public void setUmbrella(boolean umbrella) {
        this.umbrella = umbrella;
    }

}
