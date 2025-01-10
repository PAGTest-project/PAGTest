
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalendarValidator_compareQuartersTest {

    private CalendarValidator calValidator;

    @BeforeEach
    protected void setUp() {
        calValidator = new CalendarValidator();
    }

    @Test
    public void testCompareQuarters() {
        // Given
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2023, Calendar.JANUARY, 15); // Q1 2023
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2023, Calendar.APRIL, 15); // Q2 2023

        // When
        int result = calValidator.compareQuarters(cal1, cal2, 1);

        // Then
        assertEquals(-1, result, "Q1 2023 should be less than Q2 2023");
    }

    @Test
    public void testCompareQuartersSameQuarter() {
        // Given
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2023, Calendar.JANUARY, 15); // Q1 2023
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2023, Calendar.FEBRUARY, 15); // Q1 2023

        // When
        int result = calValidator.compareQuarters(cal1, cal2, 1);

        // Then
        assertEquals(0, result, "Q1 2023 should be equal to Q1 2023");
    }

    @Test
    public void testCompareQuartersDifferentYears() {
        // Given
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2022, Calendar.DECEMBER, 15); // Q4 2022
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2023, Calendar.JANUARY, 15); // Q1 2023

        // When
        int result = calValidator.compareQuarters(cal1, cal2, 1);

        // Then
        assertEquals(-1, result, "Q4 2022 should be less than Q1 2023");
    }
}
