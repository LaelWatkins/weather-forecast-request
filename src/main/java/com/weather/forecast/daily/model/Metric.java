package com.weather.forecast.daily.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metric {
    private Integer value;
    private Integer maxValue;
    private Integer minValue;
    private String unitCode;
    private String qualityControl;
}
