package com.weather.forecast.daily.model;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Summary {
    private ArrayList<DailySummary> daily = new ArrayList<>();

    public Summary(DailySummary... dailySummary){
        this.daily.addAll(Arrays.asList(dailySummary));
    }

    public Summary(){}
}
