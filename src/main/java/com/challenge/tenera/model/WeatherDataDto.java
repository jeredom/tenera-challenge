package com.challenge.tenera.model;

public class WeatherDataDto {

    private Double temperature;
    private Integer pressure;
    private boolean umbrella;

    public WeatherDataDto() {
    }

    public WeatherDataDto(Double temperature, Integer pressure, boolean umbrella) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.umbrella = umbrella;
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
