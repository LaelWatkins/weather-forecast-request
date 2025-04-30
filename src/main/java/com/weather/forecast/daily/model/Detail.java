package com.weather.forecast.daily.model;

import java.util.ArrayList;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Detail {
    private String geometry;
    private String units;
    private String forecastGenerator;
    private Date generatedAt;
    private Date updateTime;
    private Metric elevation;
    private ArrayList<Period> periods = new ArrayList<>();
}
