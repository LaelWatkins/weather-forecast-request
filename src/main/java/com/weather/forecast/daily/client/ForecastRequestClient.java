package com.weather.forecast.daily.client;

import com.weather.forecast.daily.model.Forecast;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ForecastRequestClient implements WeatherRequestClient{
    private WebClient webClient;
    private String uri;
    private static final String APPLICATION_JSONLD="application/ld+json";
    private String userAgent;

    public ForecastRequestClient(WebClient webClient, final String uri, final String userAgent ){
        this.webClient = webClient;
        this.uri = uri;
        this.userAgent = userAgent;
    }

    @Override
    public Mono<Forecast> invokeRequest() {
        return webClient.get()
            .uri(uri)
            .headers( httpHeaders -> {
                httpHeaders.add(HttpHeaders.USER_AGENT,userAgent);
                httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSONLD);
               })
            .retrieve()
            .bodyToMono(Forecast.class);
    }
}
