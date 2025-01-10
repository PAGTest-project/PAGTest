
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareMonthsTest {
    private DateValidator dateValidator;

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareMonthsEqual() {
        Date date1 = createDate(2023, Calendar.JANUARY, 15);
        Date date2 = createDate(2023, Calendar.JANUARY, 20);
        TimeZone timeZone = TimeZone.getDefault();

        assertEquals(0, dateValidator.compareMonths(date1, date2, timeZone));
    }

    @Test
    public void testCompareMonthsLessThan() {
        Date date1 = createDate(2023, Calendar.JANUARY, 15);
        Date date2 = createDate(2023, Calendar.FEBRUARY, 20);
        TimeZone timeZone = TimeZone.getDefault();

        assertEquals(-1, dateValidator.compareMonths(date1, date2, timeZone));
    }

    @Test
    public void testCompareMonthsGreaterThan() {
        Date date1 = createDate(2023, Calendar.FEBRUARY, 15);
        Date date2 = createDate(2023, Calendar.JANUARY, 20);
        TimeZone timeZone = TimeZone.getDefault();

        assertEquals(1, dateValidator.compareMonths(date1, date2, timeZone));
    }

    @Test
    public void testCompareMonthsDifferentYears() {
        Date date1 = createDate(2022, Calendar.DECEMBER, 15);
        Date date2 = createDate(2023, Calendar.JANUARY, 20);
        TimeZone timeZone = TimeZone.getDefault();

        assertEquals(-1, dateValidator.compareMonths(date1, date2, timeZone));
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
