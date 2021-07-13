package com.challenge.tenera.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "weather")
public class WeatherData {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @JsonIgnore
    public String name;
    public Double temperature;
    public Integer pressure;
    public String weatherCondition;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public WeatherData() {
        // For Jackson
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            this.createdAt = new Date();
        }
    }

}
