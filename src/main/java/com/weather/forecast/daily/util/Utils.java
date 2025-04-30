package com.weather.forecast.daily.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static synchronized boolean isCurrentDate(Date date){
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date).equals(sdf.format(currentDate));
    }

    public static synchronized String retrieveCurrentDayName(String dayDescription){
        String dayName = "";
        if(dayDescription != null && dayDescription.equalsIgnoreCase("this afternoon") || dayDescription.equalsIgnoreCase("tonight")){
            SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
            dayName = sdfDay.format(new Date());
        }
        return dayName;
    }

    public static synchronized double convertToCelsius(Integer temperatureFahrenheit){
        double celsius = 0.0;
        if(temperatureFahrenheit>0) {
            celsius= (temperatureFahrenheit - 32) * 5.0 / 9.0;  //celsius formula
        }
        return Double.valueOf(Math.round(celsius));
    }

}
