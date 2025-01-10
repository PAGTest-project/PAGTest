
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateValidator_compareYearsTest {
    private DateValidator dateValidator;

    @BeforeEach
    protected void setUp() {
        dateValidator = new DateValidator();
    }

    @Test
    public void testCompareYearsEqual() {
        Date date1 = createDate(2023, 10, 1);
        Date date2 = createDate(2023, 5, 15);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareYears(date1, date2, timeZone);
        assertEquals(0, result, "Years should be equal");
    }

    @Test
    public void testCompareYearsLessThan() {
        Date date1 = createDate(2022, 10, 1);
        Date date2 = createDate(2023, 5, 15);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareYears(date1, date2, timeZone);
        assertEquals(-1, result, "Year of date1 should be less than year of date2");
    }

    @Test
    public void testCompareYearsGreaterThan() {
        Date date1 = createDate(2024, 10, 1);
        Date date2 = createDate(2023, 5, 15);
        TimeZone timeZone = TimeZone.getDefault();

        int result = dateValidator.compareYears(date1, date2, timeZone);
        assertEquals(1, result, "Year of date1 should be greater than year of date2");
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
