package com.challenge.tenera.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    public static final String CITY_NOT_FOUND_ERROR_MESSAGE = "City not found";
    public static final String INVALID_API_KEY_ERROR_MESSAGE = "Invalid API key";
    public static final String TOO_MANY_REQUESTS_ERROR_MESSAGE = "Too many request. Only 60 request per minute are allowed";
    public static final String SERVER_ERROR_ERROR_MESSAGE = "Error getting current weather data by city name";

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

        return (httpResponse.getStatusCode().series() == Series.CLIENT_ERROR
                || httpResponse.getStatusCode().series() == Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new ResponseStatusException(httpResponse.getStatusCode(), SERVER_ERROR_ERROR_MESSAGE);
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {

            HttpStatus statusCode = httpResponse.getStatusCode();
            if (statusCode == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, CITY_NOT_FOUND_ERROR_MESSAGE);
            } else if (statusCode == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_API_KEY_ERROR_MESSAGE);
            } else if (statusCode == HttpStatus.TOO_MANY_REQUESTS) {
                throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, TOO_MANY_REQUESTS_ERROR_MESSAGE);
            } else {
                throw new ResponseStatusException(statusCode);
            }
        }
    }
}
