package com.weather.forecast.daily.client;

import com.weather.forecast.daily.model.Forecast;
import com.weather.forecast.daily.model.Summary;
import reactor.core.publisher.Mono;
import org.springframework.http.ResponseEntity;

public interface WeatherRequestClient {

    Mono<Summary> invokeRequest();
}
