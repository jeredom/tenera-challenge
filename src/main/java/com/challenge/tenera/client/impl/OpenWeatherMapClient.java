package com.challenge.tenera.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.challenge.tenera.client.IOpenWeatherMapClient;
import com.challenge.tenera.exception.RestTemplateResponseErrorHandler;
import com.challenge.tenera.model.WeatherData;

@Service
public class OpenWeatherMapClient implements IOpenWeatherMapClient {

    public static final String URL = "http://api.openweathermap.org/data/2.5/weather?q={cityName}&appid=fcc28ab0c1334af8c5d4e87988759adf";
    private RestTemplate restTemplate;

    @Autowired
    public OpenWeatherMapClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    @Override
    public WeatherData getCurrentWeatherDataByCityName(String cityName) {
        // FYI the API key is stored as plain text for simplicity reasons. I
        // would usually never store a secret in plain text but rather use
        // f.i. Spring Cloud Config, Spring Vault or provide environment
        // variables at run time.
        return restTemplate.getForObject(URL, WeatherData.class, cityName);
    }
}
