
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
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareDatesEqual() {
        Date date1 = createDate(2023, Calendar.OCTOBER, 1);
        Date date2 = createDate(2023, Calendar.OCTOBER, 1);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareDates(date1, date2, timeZone);
        assertEquals(0, result, "Dates should be equal");
    }

    @Test
    public void testCompareDatesFirstLess() {
        Date date1 = createDate(2023, Calendar.SEPTEMBER, 30);
        Date date2 = createDate(2023, Calendar.OCTOBER, 1);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareDates(date1, date2, timeZone);
        assertEquals(-1, result, "First date should be less");
    }

    @Test
    public void testCompareDatesFirstGreater() {
        Date date1 = createDate(2023, Calendar.OCTOBER, 2);
        Date date2 = createDate(2023, Calendar.OCTOBER, 1);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareDates(date1, date2, timeZone);
        assertEquals(1, result, "First date should be greater");
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
