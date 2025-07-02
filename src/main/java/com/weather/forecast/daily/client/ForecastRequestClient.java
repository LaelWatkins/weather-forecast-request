package com.weather.forecast.daily.client;

import com.weather.forecast.daily.model.DailySummary;
import com.weather.forecast.daily.model.Forecast;
import com.weather.forecast.daily.model.Summary;
import com.weather.forecast.daily.util.Utils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    public Mono<Summary> invokeRequest() {
        Mono<ResponseEntity<Forecast>> responseForecast = webClient.get()
            .uri(uri)
            .headers( httpHeaders -> {
                httpHeaders.add(HttpHeaders.USER_AGENT,userAgent);
                httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSONLD);
               })
            .retrieve()
            .toEntity(Forecast.class);

        Mono<Summary> summary = responseForecast.filter(responseEntity -> responseEntity.getStatusCode().is2xxSuccessful())
            .map(forecast -> forecast.getBody().getProperties().getPeriods().stream()
                .filter( f -> (Utils.isCurrentDate(f.getStartTime()) && Utils.isCurrentDate(f.getEndTime()) && f.getNumber().equals(1)
                    || f.getName().equalsIgnoreCase("tonight")))
                .map( period -> new Summary(new DailySummary( Utils.retrieveCurrentDayName(period.getName()),
                    Utils.convertToCelsius(period.getTemperature()),
                    period.getShortForecast())))
                .findFirst().get());
        return summary;
    }
}
