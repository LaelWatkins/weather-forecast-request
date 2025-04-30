package com.weather.forecast.daily.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Forecast {
    private Object context;
    private String type;
    private GridPattern geometry;
    private Detail properties;

    public Forecast(){}
}
