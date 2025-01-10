
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalendarValidator_adjustToTimeZoneTest {

    private CalendarValidator calValidator;

    @BeforeEach
    protected void setUp() {
        calValidator = new CalendarValidator();
    }

    @Test
    public void testAdjustToTimeZoneSameRules() {
        Calendar calendar = Calendar.getInstance();
        TimeZone originalTimeZone = calendar.getTimeZone();
        CalendarValidator.adjustToTimeZone(calendar, originalTimeZone);
        assertEquals(originalTimeZone, calendar.getTimeZone(), "Time zone should remain the same");
    }

    @Test
    public void testAdjustToTimeZoneDifferentRules() {
        Calendar calendar = Calendar.getInstance();
        TimeZone originalTimeZone = calendar.getTimeZone();
        TimeZone newTimeZone = TimeZone.getTimeZone("America/New_York");

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        CalendarValidator.adjustToTimeZone(calendar, newTimeZone);

        assertEquals(newTimeZone, calendar.getTimeZone(), "Time zone should be updated");
        assertEquals(year, calendar.get(Calendar.YEAR), "Year should remain the same");
        assertEquals(month, calendar.get(Calendar.MONTH), "Month should remain the same");
        assertEquals(date, calendar.get(Calendar.DATE), "Date should remain the same");
        assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY), "Hour should remain the same");
        assertEquals(minute, calendar.get(Calendar.MINUTE), "Minute should remain the same");
    }

    @Test
    public void testAdjustToTimeZoneDifferentRulesWithDifferentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 10, 15, 30);
        TimeZone newTimeZone = TimeZone.getTimeZone("America/New_York");

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        CalendarValidator.adjustToTimeZone(calendar, newTimeZone);

        assertEquals(newTimeZone, calendar.getTimeZone(), "Time zone should be updated");
        assertEquals(year, calendar.get(Calendar.YEAR), "Year should remain the same");
        assertEquals(month, calendar.get(Calendar.MONTH), "Month should remain the same");
        assertEquals(date, calendar.get(Calendar.DATE), "Date should remain the same");
        assertEquals(hour, calendar.get(Calendar.HOUR_OF_DAY), "Hour should remain the same");
        assertEquals(minute, calendar.get(Calendar.MINUTE), "Minute should remain the same");
    }
}
