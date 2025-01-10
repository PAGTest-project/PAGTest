
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Period;
import static org.junit.jupiter.api.Assertions.*;

public class DateAndTime_periodTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        dateAndTime = new DateAndTime(new BaseFaker());
    }

    @Test
    public void testPeriodValidRange() {
        Period min = Period.of(1, 2, 3);
        Period max = Period.of(5, 6, 7);
        Period result = dateAndTime.period(min, max);
        assertTrue(result.getYears() >= min.getYears() && result.getYears() <= max.getYears());
        assertTrue(result.getMonths() >= min.getMonths() && result.getMonths() <= max.getMonths());
        assertTrue(result.getDays() >= min.getDays() && result.getDays() <= max.getDays());
    }

    @Test
    public void testPeriodInvalidRange() {
        Period min = Period.of(5, 6, 7);
        Period max = Period.of(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> dateAndTime.period(min, max));
    }

    @Test
    public void testPeriodEqualBounds() {
        Period min = Period.of(3, 4, 5);
        Period max = Period.of(3, 4, 5);
        Period result = dateAndTime.period(min, max);
        assertEquals(min, result);
    }
}
