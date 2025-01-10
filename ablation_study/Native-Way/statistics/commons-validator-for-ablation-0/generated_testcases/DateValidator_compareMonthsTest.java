
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareMonthsTest {
    private DateValidator dateValidator;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final TimeZone EST = TimeZone.getTimeZone("EST");

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareMonths() {
        final int sameTime = 124522;
        final int testDate = 20050823;

        final Date value = createDate(GMT, testDate, sameTime); // test value
        final Date date20050901 = createDate(GMT, 20050901, sameTime); // +1 month
        final Date date20050801 = createDate(GMT, 20050801, sameTime); // same month
        final Date date20050731 = createDate(GMT, 20050731, sameTime); // -1 month

        assertEquals(-1, dateValidator.compareMonths(value, date20050901, GMT), "month LT"); // +1 month
        assertEquals(0, dateValidator.compareMonths(value, date20050801, GMT), "month EQ"); // same month
        assertEquals(1, dateValidator.compareMonths(value, date20050731, GMT), "month GT"); // -1 month

        // Compare using alternative TimeZone
        final Date sameDayTwoAm = createDate(GMT, testDate, 20000);
        assertEquals(-1, dateValidator.compareMonths(value, date20050901, EST), "month LT"); // +1 month
        assertEquals(0, dateValidator.compareMonths(value, date20050801, EST), "month EQ"); // same month
        assertEquals(1, dateValidator.compareMonths(value, date20050731, EST), "month GT"); // -1 month
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
