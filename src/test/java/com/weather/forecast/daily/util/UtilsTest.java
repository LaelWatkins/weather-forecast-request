package com.weather.forecast.daily.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void isCurrentDate() {
                                        //use the long value of yesterday to get the date object
        Date yesterdaysDate = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
        Date todaysDate = new Date();
        assertFalse(Utils.isCurrentDate(yesterdaysDate));
        assertTrue(Utils.isCurrentDate(todaysDate));
    }

    @Test
    void retrieveCurrentDayName() {
        SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
        String currentDayName = sdfDay.format(new Date());
        assertEquals(currentDayName,Utils.retrieveCurrentDayName("this afternoon"));
        assertNotEquals(currentDayName,Utils.retrieveCurrentDayName("random text"));
    }

    @Test
    void convertToCelsius() {
        int fahrenHtTemp = 85;
        assertEquals(29.0, Utils.convertToCelsius(fahrenHtTemp));
        assertNotEquals(23, Utils.convertToCelsius(fahrenHtTemp));
    }
}
