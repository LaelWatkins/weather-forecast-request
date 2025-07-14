package com.weather.forecast.daily.client;

import com.weather.forecast.daily.model.DailySummary;
import com.weather.forecast.daily.model.Detail;
import com.weather.forecast.daily.model.Forecast;
import com.weather.forecast.daily.model.GridPattern;
import com.weather.forecast.daily.model.Period;
import com.weather.forecast.daily.model.Summary;
import java.util.ArrayList;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastRequestClientTest {

    @Mock
    WebClient webClient;
    @Mock
    WebClient.RequestHeadersUriSpec uriSpec;
    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    WebClient.ResponseSpec responseSpec;

    String uri="http://localhost:8080/test";
    String userAgent="test@test.com";

    private WeatherRequestClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new ForecastRequestClient(webClient,uri,userAgent);
    }

    @Test
    void testInvokeRequest_Successfully(){
        Period p = new Period();
        p.setName("Sunny");
        p.setNumber(1);

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

        Mono<ResponseEntity<Forecast>> monoResponse = Mono.just(ResponseEntity.ok(f));
        DailySummary dailySummary =  new  DailySummary("Tuesday", 29.0, "Sunny" );
        Summary summary = new Summary(dailySummary);

        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(uri)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(Forecast.class)).thenReturn(monoResponse);
        Mono<ResponseEntity<Forecast>> testForecast = client.invokeRequest();

        assertNotNull(testForecast);

    }

}
