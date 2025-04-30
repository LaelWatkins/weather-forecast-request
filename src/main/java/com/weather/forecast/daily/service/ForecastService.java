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

@Service
@Slf4j
public class ForecastService {

    private WeatherRequestClient client;
    private ObjectMapper objectMapper;

    public ForecastService(WeatherRequestClient client, ObjectMapper mapper){
        this.client = client;
        this.objectMapper = mapper;
    }

    public String retrieveDailyForecast() throws JsonProcessingException {
        try {
            Forecast forecast = client.invokeRequest().block();
            Summary summary =  forecast.getProperties().getPeriods().stream()
                                     .filter( f -> (Utils.isCurrentDate(f.getStartTime()) &&
                                                     Utils.isCurrentDate(f.getEndTime()) && f.getNumber().equals(1) ||
                                                     f.getName().equalsIgnoreCase("tonight")))
                                     .map( period -> new Summary(new DailySummary( Utils.retrieveCurrentDayName(period.getName()),
                                                                       Utils.convertToCelsius(period.getTemperature()),
                                                                       period.getShortForecast())))
                                     .findFirst().get();
            String dailySummaryString = objectMapper.writeValueAsString(summary);

            return dailySummaryString;
        } catch (RuntimeException e){
            throw e ;
        }
    }
}
