package com.weather.forecast.daily.controller;

import com.weather.forecast.daily.model.DailySummary;
import com.weather.forecast.daily.model.Forecast;
import com.weather.forecast.daily.model.Summary;
import com.weather.forecast.daily.service.ForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ForecastController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ForecastControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ForecastService service;

    private ForecastController controller;
    private static final String URL = "http://localhost:8080/api/v1/weather/forecast/daily";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ForecastController(service);
    }

    @Test
    void requestDailyForecast_Sucessfully() throws Exception {
        DailySummary dailySummary =  new  DailySummary("Tuesday", 29.0, "Sunny" );
        Summary summary = new Summary(dailySummary);
        when(service.retrieveDailyForecast()).thenReturn(Mono.just(summary));

        mockMvc.perform(get(URL))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
