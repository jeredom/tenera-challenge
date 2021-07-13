package com.challenge.tenera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.tenera.model.WeatherDataDto;
import com.challenge.tenera.model.WeatherDataHistoryDto;
import com.challenge.tenera.service.IWeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private IWeatherService weatherService;

    @Operation(operationId = "getCurrentWeatherByCityName", summary = "Gets the current weather data by city name in the form of e.g. \"Berlin\" or \"Berlin,de\"")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found weather data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = WeatherDataDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Invalid API key", content = @Content),
            @ApiResponse(responseCode = "404", description = "The city was not found", content = @Content),
            @ApiResponse(responseCode = "428", description = " Only 60 request per minute are allowed", content = @Content) })
    @GetMapping("/current")
    public ResponseEntity<WeatherDataDto> getCurrentWeatherByCityName(@RequestParam String cityName) {
        return ResponseEntity.ok(weatherService.getCurrentWeatherDataByCityName(cityName));
    }

    @Operation(operationId = "getWeatherDataHistoryByCityName", summary = "Gets the historic weather data of the last 5 queries for given city name in the "
            + "form of e.g. \"Berlin\" or \"Berlin,de\". Also contains the average temperature and pressure for this period.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found weather data history", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = WeatherDataHistoryDto.class)) }),
            @ApiResponse(responseCode = "404", description = "The city was not found", content = @Content) })
    @GetMapping("/history")
    public ResponseEntity<WeatherDataHistoryDto> getWeatherDataHistoryByCityName(@RequestParam String cityName) {
        return ResponseEntity.ok(weatherService.getWeatherDataHistoryByCityName(cityName));
    }

}
