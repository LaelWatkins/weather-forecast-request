package com.weather.forecast.daily.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.forecast.daily.client.WeatherRequestClient;
import com.weather.forecast.daily.model.DailySummary;
import com.weather.forecast.daily.model.Detail;
import com.weather.forecast.daily.model.Forecast;
import com.weather.forecast.daily.model.GridPattern;
import com.weather.forecast.daily.model.Period;
import com.weather.forecast.daily.model.Summary;
import com.weather.forecast.daily.util.Utils;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {

    @Mock
    private WeatherRequestClient client;

    private ForecastService service;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        service = new ForecastService(client,mapper);
    }

    @Test
    void testInvokeDailyForecast_Successfully() throws JsonProcessingException {

        String currentDayName = Utils.retrieveCurrentDayName("This afternoon");

        Period p = createPeriod();
        ArrayList<Period> periods = new ArrayList<>();
        periods.add(p);

        Detail d = new Detail();
        d.setUnits("us");
        d.setForecastGenerator("BaseLineForecastGenerator");
        d.setPeriods(periods);

        Forecast f = new Forecast();
        f.setType("Feature");
        f.setProperties(d);
        f.setGeometry(new GridPattern());

        Summary summary = new Summary();
        DailySummary ds = new DailySummary(currentDayName,20.0,"Mostly Cloudy");
        ArrayList<DailySummary> summaries = new ArrayList<>();
        summaries.add(ds);
        summary.setDaily(summaries);

        String dailySummaryString = String.format("{\"day_name\":\"%s\",\"temp_high_celsius\":20.0,\"forecast_blurp\":\"Mostly Cloudy\"}",currentDayName);

        when(client.invokeRequest()).thenReturn(Mono.just(f));
        String dailySummary = service.retrieveDailyForecast();

        assertNotNull(dailySummary);
        assertThat(dailySummary).contains(currentDayName);
        assertThat(dailySummary).contains("20.0");
        assertThat(dailySummary).contains("Mostly Cloudy");
    }

    Period createPeriod() throws JsonProcessingException {

        String periodString = "{\n" +
            "                \"number\": 1,\n" +
            "                \"name\": \"Tonight\",\n" +
            "                \"startTime\": \"2025-04-28T21:00:00-04:00\",\n" +
            "                \"endTime\": \"2025-04-29T06:00:00-04:00\",\n" +
            "                \"isDaytime\": false,\n" +
            "                \"temperature\": 68,\n" +
            "                \"temperatureUnit\": \"F\",\n" +
            "                \"temperatureTrend\": \"\",\n" +
            "                \"probabilityOfPrecipitation\": {\n" +
            "                    \"unitCode\": \"wmoUnit:percent\",\n" +
            "                    \"value\": null\n" +
            "                },\n" +
            "                \"windSpeed\": \"5 to 10 mph\",\n" +
            "                \"windDirection\": \"E\",\n" +
            "                \"icon\": \"https://api.weather.gov/icons/land/night/bkn?size=medium\",\n" +
            "                \"shortForecast\": \"Mostly Cloudy\",\n" +
            "                \"detailedForecast\": \"Mostly cloudy, with a low around 68. East wind 5 to 10 mph.\"\n" +
            "            }";

        Period period = mapper.readValue(periodString, Period.class);
        return period;
    }
}
