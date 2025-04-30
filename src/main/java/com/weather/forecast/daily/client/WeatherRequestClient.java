package com.weather.forecast.daily.client;

import com.weather.forecast.daily.model.Forecast;
import reactor.core.publisher.Mono;

public interface WeatherRequestClient {

    Mono<Forecast> invokeRequest();
}
