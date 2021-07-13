package com.challenge.tenera.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.tenera.converter.impl.ModelConverter;
import com.challenge.tenera.model.WeatherData;
import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;

@SpringBootTest
public class ModelConverterTest {

    @Autowired
    private ModelConverter modelConverter;

    @Test
    public void shouldConvertNullWeatherData() {
        WeatherData weatherData = null;
        WeatherDataDto weatherDataDto = modelConverter.convert(weatherData);
        assertEquals(null, weatherDataDto.getPressure());
        assertEquals(null, weatherDataDto.getTemperature());
        assertEquals(false, weatherDataDto.isUmbrella());
    }

    @Test
    public void ShouldConvertEmptyWeatherData() {
        WeatherDataDto weatherDataDto = modelConverter.convert(new WeatherData());
        assertEquals(null, weatherDataDto.getPressure());
        assertEquals(null, weatherDataDto.getTemperature());
        assertEquals(false, weatherDataDto.isUmbrella());
    }

    @Test
    public void ShouldConvertWeatherDataWhenUmbrellaIsNeeded() {
        WeatherDataDto weatherDataDto = modelConverter.convert(createWeatherData(1, 1d, "Drizzle"));

        assertEquals(1, weatherDataDto.getPressure());
        assertEquals(1d, weatherDataDto.getTemperature());
        assertEquals(true, weatherDataDto.isUmbrella());
    }

    @Test
    public void ShouldConvertWeatherDataWhenUmbrellaIsNotNeeded() {
        WeatherDataDto weatherDataDto = modelConverter.convert(createWeatherData(1, 1d, "Clouds"));

        assertEquals(1, weatherDataDto.getPressure());
        assertEquals(1d, weatherDataDto.getTemperature());
        assertEquals(false, weatherDataDto.isUmbrella());
    }

    @Test
    public void ShouldConvertEmptyWeatherDataHistory() {
        List<WeatherData> history = new LinkedList<>();
        WeatherDataHistoryDto weatherDataHistoryDto = modelConverter.convert(history);

        assertEquals(null, weatherDataHistoryDto);
    }

    @Test
    public void ShouldConvertWeatherDataHistory() {
        List<WeatherData> history = new LinkedList<>();
        history.add(createWeatherData(5, 5d, "Drizzle"));
        history.add(createWeatherData(5, 5d, "Drizzle"));
        history.add(createWeatherData(5, 5d, "Drizzle"));
        WeatherDataHistoryDto weatherDataHistoryDto = modelConverter.convert(history);

        assertEquals(5, weatherDataHistoryDto.getAveragePressure());
        assertEquals(5, weatherDataHistoryDto.getAverageTemperature());
        assertEquals(3, weatherDataHistoryDto.getHistory().size());

        for (WeatherDataDto data : weatherDataHistoryDto.getHistory()) {
            assertEquals(5, data.getPressure());
            assertEquals(5, data.getTemperature());
            assertEquals(true, data.isUmbrella());
        }
    }

    @Test
    public void ShouldConvertWeatherDataHistoryIfTemperatureAndPressuerAreNull() {
        List<WeatherData> history = new LinkedList<>();
        history.add(createWeatherData(null, null, "Drizzle"));
        history.add(createWeatherData(3, 3d, "Drizzle"));
        history.add(createWeatherData(3, 3d, "Drizzle"));
        WeatherDataHistoryDto weatherDataHistoryDto = modelConverter.convert(history);

        assertEquals(2, weatherDataHistoryDto.getAveragePressure());
        assertEquals(2, weatherDataHistoryDto.getAverageTemperature());
        assertEquals(3, weatherDataHistoryDto.getHistory().size());
    }

    private WeatherData createWeatherData(Integer pressure, Double temperature, String weatherCondition) {
        WeatherData weatherData = new WeatherData();
        weatherData.setPressure(pressure);
        weatherData.setTemperature(temperature);
        weatherData.setWeatherCondition(weatherCondition);
        return weatherData;
    }

}
