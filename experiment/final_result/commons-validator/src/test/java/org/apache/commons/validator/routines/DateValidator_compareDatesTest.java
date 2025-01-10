
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareDatesTest {

    private DateValidator dateValidator;

    @BeforeEach
    public void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareDatesEqual() {
        Date date1 = createDate(2023, Calendar.JANUARY, 1);
        Date date2 = createDate(2023, Calendar.JANUARY, 1);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareDates(date1, date2, timeZone);
        assertEquals(0, result, "Dates should be equal");
    }

    @Test
    public void testCompareDatesLessThan() {
        Date date1 = createDate(2023, Calendar.JANUARY, 1);
        Date date2 = createDate(2023, Calendar.FEBRUARY, 1);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareDates(date1, date2, timeZone);
        assertEquals(-1, result, "First date should be less than second date");
    }

    @Test
    public void testCompareDatesGreaterThan() {
        Date date1 = createDate(2023, Calendar.FEBRUARY, 1);
        Date date2 = createDate(2023, Calendar.JANUARY, 1);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareDates(date1, date2, timeZone);
        assertEquals(1, result, "First date should be greater than second date");
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
