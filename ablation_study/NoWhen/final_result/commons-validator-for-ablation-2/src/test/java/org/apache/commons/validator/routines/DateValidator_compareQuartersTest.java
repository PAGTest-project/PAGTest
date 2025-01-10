
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareQuartersTest {
    private DateValidator dateValidator;

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareQuartersEqual() {
        Date date1 = createDate(2023, Calendar.JANUARY, 15);
        Date date2 = createDate(2023, Calendar.FEBRUARY, 15);
        TimeZone timeZone = TimeZone.getDefault();
        int monthOfFirstQuarter = 1;

        int result = dateValidator.compareQuarters(date1, date2, timeZone, monthOfFirstQuarter);
        assertEquals(0, result, "Quarters should be equal");
    }

    @Test
    public void testCompareQuartersLessThan() {
        Date date1 = createDate(2023, Calendar.JANUARY, 15);
        Date date2 = createDate(2023, Calendar.APRIL, 15);
        TimeZone timeZone = TimeZone.getDefault();
        int monthOfFirstQuarter = 1;

        int result = dateValidator.compareQuarters(date1, date2, timeZone, monthOfFirstQuarter);
        assertEquals(-1, result, "First quarter should be less than second");
    }

    @Test
    public void testCompareQuartersGreaterThan() {
        Date date1 = createDate(2023, Calendar.APRIL, 15);
        Date date2 = createDate(2023, Calendar.JANUARY, 15);
        TimeZone timeZone = TimeZone.getDefault();
        int monthOfFirstQuarter = 1;

        int result = dateValidator.compareQuarters(date1, date2, timeZone, monthOfFirstQuarter);
        assertEquals(1, result, "First quarter should be greater than second");
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
