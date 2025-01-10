
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareWeeksTest {
    private DateValidator dateValidator;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareWeeks() {
        final int sameTime = 124522;
        final int testDate = 20050823;

        final Date value = createDate(GMT, testDate, sameTime); // test value
        final Date date20050830 = createDate(GMT, 20050830, sameTime); // +1 week
        final Date date20050816 = createDate(GMT, 20050816, sameTime); // -1 week

        assertEquals(-1, dateValidator.compareWeeks(value, date20050830, GMT), "week LT"); // +1 week
        assertEquals(0, dateValidator.compareWeeks(value, createDate(GMT, 20050824, sameTime), GMT), "week =1"); // +1 day
        assertEquals(0, dateValidator.compareWeeks(value, createDate(GMT, 20050822, sameTime), GMT), "week =2"); // same week
        assertEquals(0, dateValidator.compareWeeks(value, createDate(GMT, 20050822, sameTime), GMT), "week =3"); // -1 day
        assertEquals(1, dateValidator.compareWeeks(value, date20050816, GMT), "week GT"); // -1 week
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
