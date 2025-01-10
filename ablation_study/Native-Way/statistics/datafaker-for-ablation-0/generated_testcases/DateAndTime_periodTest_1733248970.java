
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Period;
import static org.junit.jupiter.api.Assertions.*;

public class DateAndTime_periodTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        dateAndTime = new DateAndTime(faker);
    }

    @Test
    public void testPeriodValidRange() {
        Period min = Period.of(1, 2, 3);
        Period max = Period.of(4, 5, 6);

        Period result = dateAndTime.period(min, max);

        assertTrue(result.getYears() >= min.getYears() && result.getYears() <= max.getYears());
        assertTrue(result.getMonths() >= min.getMonths() && result.getMonths() <= max.getMonths());
        assertTrue(result.getDays() >= min.getDays() && result.getDays() <= max.getDays());
    }

    @Test
    public void testPeriodInvalidRange() {
        Period min = Period.of(4, 5, 6);
        Period max = Period.of(1, 2, 3);

        assertThrows(IllegalArgumentException.class, () -> {
            dateAndTime.period(min, max);
        });
    }

    @Test
    public void testPeriodEqualMinMax() {
        Period min = Period.of(2, 3, 4);
        Period max = Period.of(2, 3, 4);

        Period result = dateAndTime.period(min, max);

        assertEquals(min, result);
    }
}
