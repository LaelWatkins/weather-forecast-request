package com.weather.forecast.daily.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.forecast.daily.model.Forecast;
import com.weather.forecast.daily.service.ForecastService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/api/v1/weather/forecast")
@Slf4j
public class ForecastController {

    private ForecastService service;

    public ForecastController(ForecastService service ) {
        this.service = service;
    }

    @GetMapping(value="daily", produces= MediaType.APPLICATION_JSON_VALUE)
    public String requestDailyForecast() throws JsonProcessingException {
        log.info("Daily forecast Request received");
        return service.retrieveDailyForecast();
    }

}
