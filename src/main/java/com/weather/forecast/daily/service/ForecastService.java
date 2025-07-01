package com.weather.forecast.daily.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.forecast.daily.client.WeatherRequestClient;
import com.weather.forecast.daily.model.DailySummary;
import com.weather.forecast.daily.model.Forecast;
import com.weather.forecast.daily.model.Summary;
import com.weather.forecast.daily.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ForecastService {

    private WeatherRequestClient client;

    public ForecastService(WeatherRequestClient client){
        this.client = client;
    }

    public Mono<Summary> retrieveDailyForecast() throws JsonProcessingException {
        try {
           return client.invokeRequest();
        } catch (RuntimeException e){
            throw e ;
        }
    }
}
