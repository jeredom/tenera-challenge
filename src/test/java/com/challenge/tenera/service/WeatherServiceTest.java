package com.challenge.tenera.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;
import com.challenge.tenera.service.impl.WeatherService;

@SpringBootTest
public class WeatherServiceTest {

    private static final String API_ROOT = "http://localhost:8080/weather";
    private static final String CURRENT_PATH = "/current";
    private static final String HISTORY_PATH = "/history";

    @Autowired
    private WeatherService weatherService;

    // @Test
    // public void whenGetAllFeaturesThenOk() {
    // Response response = RestAssured.get(API_ROOT);
    // assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    // }

    @Test
    public void shouldGetCurrentWeatherDataByCityName() {
        WeatherDataDto dto = weatherService.getCurrentWeatherDataByCityName("Berlin");
        assertNotNull(dto);
    }

    @Test
    public void shouldThrowNotFoundIfCityIsUnknownGettingCurrent() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            weatherService.getCurrentWeatherDataByCityName("UnknownCity");
        });
        assertExceptionStatusAndReasonEqual(exception, HttpStatus.NOT_FOUND, "City not found");
    }

    @Test
    public void shouldGetWeatherDataHistoryByCityName() {
        WeatherDataHistoryDto dto = weatherService.getWeatherDataHistoryByCityName("Berlin");
        assertNotNull(dto);
    }

    @Test
    public void shouldThrowNotFoundIfCityIsUnknownGettingHistory() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            weatherService.getWeatherDataHistoryByCityName("UnknownCity");
        });
        assertExceptionStatusAndReasonEqual(exception, HttpStatus.NOT_FOUND, "City not found");
    }

    private void assertExceptionStatusAndReasonEqual(ResponseStatusException exception, HttpStatus status, String reason) {
        assertEquals(status, exception.getStatus());
        assertEquals(reason, exception.getReason());
    }
}
