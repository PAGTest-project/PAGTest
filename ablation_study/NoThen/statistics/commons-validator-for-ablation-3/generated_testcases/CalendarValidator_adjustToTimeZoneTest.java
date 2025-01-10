
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        TimeZone newTimeZone = TimeZone.getTimeZone("PST");
        CalendarValidator.adjustToTimeZone(calendar, newTimeZone);
        assertEquals(newTimeZone, calendar.getTimeZone(), "Time zone should be adjusted");
        assertEquals(calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR), "Year should remain the same");
        assertEquals(calendar.get(Calendar.MONTH), calendar.get(Calendar.MONTH), "Month should remain the same");
        assertEquals(calendar.get(Calendar.DATE), calendar.get(Calendar.DATE), "Date should remain the same");
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.HOUR_OF_DAY), "Hour should remain the same");
        assertEquals(calendar.get(Calendar.MINUTE), calendar.get(Calendar.MINUTE), "Minute should remain the same");
    }
}
