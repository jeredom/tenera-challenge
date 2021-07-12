package com.challenge.tenera.client.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.challenge.tenera.client.IOpenWeatherMapClient;
import com.challenge.tenera.model.WeatherData;

@Service
public class OpenWeatherMapClient implements IOpenWeatherMapClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherData getCurrentWeatherDataByCityName(String cityName) {
        WeatherData weatherData = null;
        try {
            // FYI the API key is stored as plain text for simplicity reasons. I
            // would usually never store a secret in plain text but rather use
            // f.i. Spring Cloud Config, Spring Vault or provide environment
            // variables at run time.
            weatherData = restTemplate.getForObject(
                    "http://api.openweathermap.org/data/2.5/weather?q={cityName}&appid=fcc28ab0c1334af8c5d4e87988759adf",
                    WeatherData.class, cityName);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found");
            } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API key");
            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
                        "Too many request. Only 60 request per minute are allowed");
            } else {
                throw new ResponseStatusException(e.getStatusCode());
            }
        } catch (HttpServerErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), "Error getting current weather data by city name");
        }
        return weatherData;
    }
}
