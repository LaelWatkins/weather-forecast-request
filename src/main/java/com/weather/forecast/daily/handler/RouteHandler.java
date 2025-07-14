package com.weather.forecast.daily.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.forecast.daily.service.ForecastService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class RouteHandler {

    private final ForecastService forecastService;

    public RouteHandler(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    public Mono<ServerResponse> handleDailyForecast(ServerRequest request) {
        log.info("Daily forecast request received via RouteHandler");
        return forecastService.retrieveDailyForecast()
                .flatMap(summary -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(summary))
                .onErrorResume(JsonProcessingException.class, e -> {
                    log.error("Error processing daily forecast request {}", e.getMessage());
                    return ServerResponse.status(500)
                            .bodyValue("Error processing forecast request");
                })
                .onErrorResume(RuntimeException.class, e -> {
                    log.error("Runtime error during forecast processing {}", e.getMessage());
                    return ServerResponse.status(503)
                            .bodyValue("Service temporarily unavailable");
                });
    }
}
