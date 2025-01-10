
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareYearsTest {
    private DateValidator dateValidator;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final TimeZone EST = TimeZone.getTimeZone("EST");

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareYears() {
        final int sameTime = 124522;
        final int testDate = 20050823;

        final Date value = createDate(GMT, testDate, sameTime); // test value
        final Date date20060101 = createDate(GMT, 20060101, sameTime); // +1 year
        final Date date20050101 = createDate(GMT, 20050101, sameTime); // same year
        final Date date20041231 = createDate(GMT, 20041231, sameTime); // -1 year

        assertEquals(-1, dateValidator.compareYears(value, date20060101, GMT), "year LT"); // +1 year
        assertEquals(0, dateValidator.compareYears(value, date20050101, GMT), "year EQ"); // same year
        assertEquals(1, dateValidator.compareYears(value, date20041231, GMT), "year GT"); // -1 year

        // Compare using alternative TimeZone
        final Date sameDayTwoAm = createDate(GMT, testDate, 20000);
        assertEquals(-1, dateValidator.compareYears(value, date20060101, EST), "year LT"); // +1 year
        assertEquals(0, dateValidator.compareYears(value, date20050101, EST), "year EQ"); // same year
        assertEquals(1, dateValidator.compareYears(value, date20041231, EST), "year GT"); // -1 year
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
