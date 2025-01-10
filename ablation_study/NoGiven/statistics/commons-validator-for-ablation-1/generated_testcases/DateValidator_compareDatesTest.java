
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareDatesTest {
    private DateValidator dateValidator;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final TimeZone EST = TimeZone.getTimeZone("EST");

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareDates_SameDay() {
        Date date1 = createDate(GMT, 2023, Calendar.OCTOBER, 10, 12, 0, 0);
        Date date2 = createDate(GMT, 2023, Calendar.OCTOBER, 10, 14, 0, 0);
        assertEquals(0, dateValidator.compareDates(date1, date2, GMT), "Same day, different time");
    }

    @Test
    public void testCompareDates_DifferentDays() {
        Date date1 = createDate(GMT, 2023, Calendar.OCTOBER, 10, 12, 0, 0);
        Date date2 = createDate(GMT, 2023, Calendar.OCTOBER, 11, 12, 0, 0);
        assertEquals(-1, dateValidator.compareDates(date1, date2, GMT), "Earlier day");
        assertEquals(1, dateValidator.compareDates(date2, date1, GMT), "Later day");
    }

    @Test
    public void testCompareDates_DifferentTimeZones() {
        Date date1 = createDate(GMT, 2023, Calendar.OCTOBER, 10, 12, 0, 0);
        Date date2 = createDate(EST, 2023, Calendar.OCTOBER, 10, 12, 0, 0);
        assertEquals(0, dateValidator.compareDates(date1, date2, GMT), "Same day, different time zones");
    }

    private Date createDate(TimeZone timeZone, int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.set(year, month, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
