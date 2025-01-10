
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Period;
import static org.junit.jupiter.api.Assertions.*;

public class TimeAndDate_periodTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
    }

    @Test
    public void testPeriodValidRange() {
        Period min = Period.of(1, 2, 3);
        Period max = Period.of(5, 6, 7);
        Period result = timeAndDate.period(min, max);

        assertTrue(result.getYears() >= min.getYears() && result.getYears() <= max.getYears());
        assertTrue(result.getMonths() >= min.getMonths() && result.getMonths() <= max.getMonths());
        assertTrue(result.getDays() >= min.getDays() && result.getDays() <= max.getDays());
    }

    @Test
    public void testPeriodInvalidRange() {
        Period min = Period.of(5, 6, 7);
        Period max = Period.of(1, 2, 3);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            timeAndDate.period(min, max);
        });

        assertEquals("Max period(" + max + ") should be not less than min (" + min + ")", exception.getMessage());
    }
}
