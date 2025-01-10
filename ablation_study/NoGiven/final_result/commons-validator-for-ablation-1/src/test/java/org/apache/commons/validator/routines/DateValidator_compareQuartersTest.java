
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareQuartersTest {
    private DateValidator dateValidator;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final TimeZone EST = TimeZone.getTimeZone("EST");

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareQuarters() {
        final int sameTime = 124522;
        final int testDate = 20050823;

        final Date value = createDate(GMT, testDate, sameTime); // test value
        final Date date20051101 = createDate(GMT, 20051101, sameTime); // +1 quarter (Feb Start)
        final Date date20051001 = createDate(GMT, 20051001, sameTime); // +1 quarter
        final Date date20050701 = createDate(GMT, 20050701, sameTime); // same quarter
        final Date date20050630 = createDate(GMT, 20050630, sameTime); // -1 quarter
        final Date date20050110 = createDate(GMT, 20050110, sameTime); // Previous Year qtr (Fen start)

        assertEquals(-1, dateValidator.compareQuarters(value, date20051101, GMT), "qtrA <1"); // +1 quarter (Feb)
        assertEquals(-1, dateValidator.compareQuarters(value, date20051001, GMT), "qtrA <2"); // +1 quarter
        assertEquals(0, dateValidator.compareQuarters(value, date20050701, GMT), "qtrA =2"); // same quarter
        assertEquals(1, dateValidator.compareQuarters(value, date20050630, GMT), "qtrA GT"); // -1 quarter

        // Change quarter 1 to start in Feb
        assertEquals(-1, dateValidator.compareQuarters(value, date20051101, GMT, 2), "qtrB LT"); // +1 quarter (Feb)
        assertEquals(0, dateValidator.compareQuarters(value, date20051001, GMT, 2), "qtrB =1"); // same quarter
        assertEquals(1, dateValidator.compareQuarters(value, date20050701, GMT, 2), "qtrB =3"); // same quarter
        assertEquals(1, dateValidator.compareQuarters(value, date20050630, GMT, 2), "qtrB GT"); // -1 quarter
        assertEquals(1, dateValidator.compareQuarters(value, date20050110, GMT, 2), "qtrB prev"); // Jan Prev year qtr
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
