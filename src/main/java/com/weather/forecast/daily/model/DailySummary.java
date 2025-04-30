package com.weather.forecast.daily.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@RequiredArgsConstructor
public class DailySummary {
    private String dayName;
    private double tempHighCelsius;
    private String forecastBlurp;

    public DailySummary(final String dayName, final double tempHighCelsius, final String forecastBlurp ){
        this.dayName = dayName;
        this.tempHighCelsius = tempHighCelsius;
        this.forecastBlurp = forecastBlurp;
    }

   @JsonProperty("day_name")
    public String getDayName() {
        return dayName;
    }

    @JsonProperty("temp_high_celsius")
    public double getTempHighCelsius() {
        return tempHighCelsius;
    }

    @JsonProperty("forecast_blurp")
    public String getForecastBlurp() {
        return forecastBlurp;
    }
}
