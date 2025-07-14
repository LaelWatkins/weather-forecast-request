package com.weather.forecast.daily.config;

import com.weather.forecast.daily.handler.RouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class WebFluxConfig {
    @Bean
    public RouterFunction<ServerResponse> weatherRoutes(RouteHandler routeHandler) {
        return RouterFunctions
            .route()
            .GET("/api/v1/weather/forecast/daily",accept(MediaType.APPLICATION_JSON), routeHandler::handleDailyForecast)
            .build();
    }
}
