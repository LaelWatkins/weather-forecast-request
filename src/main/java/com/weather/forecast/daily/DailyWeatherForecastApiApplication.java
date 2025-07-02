package com.weather.forecast.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DailyWeatherForecastApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyWeatherForecastApiApplication.class, args);
	}

}
