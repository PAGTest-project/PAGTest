
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
    public void testCompareDates() {
        final int sameTime = 124522;
        final int testDate = 20050823;
        final Date diffHour = createDate(GMT, testDate, 115922); // same date, different time

        final Date value = createDate(GMT, testDate, sameTime); // test value
        final Date date20050824 = createDate(GMT, 20050824, sameTime); // +1 day
        final Date date20050822 = createDate(GMT, 20050822, sameTime); // -1 day

        assertEquals(-1, dateValidator.compareDates(value, date20050824, GMT), "date LT"); // +1 day
        assertEquals(0, dateValidator.compareDates(value, diffHour, GMT), "date EQ"); // same day, diff hour
        assertEquals(1, dateValidator.compareDates(value, date20050822, GMT), "date GT"); // -1 day

        // Compare using alternative TimeZone
        final Date sameDayTwoAm = createDate(GMT, testDate, 20000);
        assertEquals(-1, dateValidator.compareDates(value, date20050824, EST), "date LT"); // +1 day
        assertEquals(0, dateValidator.compareDates(value, diffHour, EST), "date EQ"); // same day, diff hour
        assertEquals(1, dateValidator.compareDates(value, sameDayTwoAm, EST), "date EQ"); // same day, diff hour
        assertEquals(1, dateValidator.compareDates(value, date20050822, EST), "date GT"); // -1 day
    }

    private Date createDate(TimeZone timeZone, int date, int time) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.set(Calendar.YEAR, date / 10000);
        calendar.set(Calendar.MONTH, (date % 10000) / 100 - 1);
        calendar.set(Calendar.DAY_OF_MONTH, date % 100);
        calendar.set(Calendar.HOUR_OF_DAY, time / 10000);
        calendar.set(Calendar.MINUTE, (time % 10000) / 100);
        calendar.set(Calendar.SECOND, time % 100);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
