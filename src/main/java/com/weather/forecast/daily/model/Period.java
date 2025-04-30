package com.weather.forecast.daily.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Period {
    private Integer number;
    private String name;
    private Date startTime;
    private Date endTime;
    @JsonProperty("isDaytime")
    private boolean daytime;
    private Integer temperature;
    private String temperatureUnit;
    private String temperatureTrend;
    private Metric probabilityOfPrecipitation;
    private String windSpeed;
    private String windDirection;
    private String icon;
    private String shortForecast;
    private String detailedForecast;



}
