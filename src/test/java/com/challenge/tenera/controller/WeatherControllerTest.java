package com.challenge.tenera.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetCurrentWeatherDataByCityName() {
        WeatherDataDto dto = this.restTemplate.getForObject("http://localhost:" + port + "/weather/current?cityName=Berlin",
                WeatherDataDto.class);
        assertNotNull(dto);
    }

    @Test
    public void shouldGetWeatherDataHistoryByCityName() {
        WeatherDataHistoryDto dto = this.restTemplate
                .getForObject("http://localhost:" + port + "/weather/history?cityName=Berlin", WeatherDataHistoryDto.class);
        assertNotNull(dto);
    }
}
