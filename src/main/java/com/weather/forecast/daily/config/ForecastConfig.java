package com.weather.forecast.daily.config;

import reactor.netty.http.client.HttpClient;

import com.weather.forecast.daily.client.ForecastRequestClient;
import com.weather.forecast.daily.client.WeatherRequestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;


@Configuration
public class ForecastConfig {

    @Value("${app.forecast.uri}")
    private String uri;
    @Value("${app.forecast.userAgent.email}")
    private String userAgentEmail;

    @Bean
    @DependsOn(value = "webClient")
    public WeatherRequestClient weatherRequestClient(){
        return new ForecastRequestClient(webClient(),uri,userAgentEmail);
    }

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }
}
