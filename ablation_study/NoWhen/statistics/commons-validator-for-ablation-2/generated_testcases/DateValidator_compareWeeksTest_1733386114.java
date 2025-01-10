
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareWeeksTest {
    private DateValidator dateValidator;

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareWeeksSameWeek() {
        Date date1 = createDate(2023, Calendar.OCTOBER, 1); // Sunday
        Date date2 = createDate(2023, Calendar.OCTOBER, 2); // Monday
        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        int result = dateValidator.compareWeeks(date1, date2, timeZone);
        assertEquals(0, result, "Dates should be in the same week");
    }

    @Test
    public void testCompareWeeksDifferentWeeks() {
        Date date1 = createDate(2023, Calendar.OCTOBER, 1); // Sunday
        Date date2 = createDate(2023, Calendar.OCTOBER, 8); // Sunday of next week
        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        int result = dateValidator.compareWeeks(date1, date2, timeZone);
        assertEquals(-1, result, "First date should be in an earlier week");
    }

    @Test
    public void testCompareWeeksDifferentYears() {
        Date date1 = createDate(2022, Calendar.DECEMBER, 31); // Saturday
        Date date2 = createDate(2023, Calendar.JANUARY, 1); // Sunday
        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        int result = dateValidator.compareWeeks(date1, date2, timeZone);
        assertEquals(-1, result, "First date should be in an earlier week");
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
