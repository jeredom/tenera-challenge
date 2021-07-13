package com.challenge.tenera.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.challenge.tenera.dao.IWeatherDataDao;
import com.challenge.tenera.model.WeatherData;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherDataDaoIntegrationTest {

    @Autowired
    private IWeatherDataDao weatherDataDao;

    @Test
    public void shouldSaveWeatherData() {
        WeatherData weatherData = new WeatherData();
        weatherData.setName("Berlin");
        weatherData.setPressure(1);
        weatherData.setTemperature(1d);
        weatherData.setWeatherCondition("Drizzle");
        WeatherData persistedWeatherData = weatherDataDao.save(createWeatherData(1, 1d, "Drizzle", "Berlin"));

        assertNotNull(persistedWeatherData);
        assertEquals(weatherData.getName(), persistedWeatherData.getName());
        assertEquals(weatherData.getPressure(), persistedWeatherData.getPressure());
        assertEquals(weatherData.getTemperature(), persistedWeatherData.getTemperature());
        assertEquals(weatherData.getWeatherCondition(), persistedWeatherData.getWeatherCondition());
    }

    @Test
    public void shouldFindTop5ByNameOrderByCreatedAtDesc() {
        weatherDataDao.save(createWeatherData(1, 1d, "Drizzle", "Berlin"));
        weatherDataDao.save(createWeatherData(2, 2d, "Drizzle", "Berlin"));
        weatherDataDao.save(createWeatherData(3, 3d, "Drizzle", "Berlin"));
        List<WeatherData> history = weatherDataDao.findTop5ByNameOrderByCreatedAtDesc("Berlin");

        assertNotNull(history);

        for (int i = 3; i <= 1; i--) {
            assertEquals(history.get(i).getName(), "Berlin");
            assertEquals(history.get(i).getPressure(), i);
            assertEquals(history.get(i).getTemperature(), i);
            assertEquals(history.get(i).getWeatherCondition(), "Drizzle");
        }
    }

    private WeatherData createWeatherData(Integer pressure, Double temperature, String weatherCondition, String name) {
        WeatherData weatherData = new WeatherData();
        weatherData.setPressure(pressure);
        weatherData.setTemperature(temperature);
        weatherData.setWeatherCondition(weatherCondition);
        weatherData.setName(name);
        return weatherData;
    }
}
