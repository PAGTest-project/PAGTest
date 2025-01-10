
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

public class TimeAndDate_betweenTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders() {});
    }

    @Test
    public void testBetweenValidRange() {
        Instant from = Instant.now();
        Instant to = from.plusMillis(1000);

        Instant result = timeAndDate.between(from, to);
        assertTrue(result.isAfter(from) || result.equals(from));
        assertTrue(result.isBefore(to));
    }

    @Test
    public void testBetweenEqualDates() {
        Instant from = Instant.now();
        Instant to = from;

        Instant result = timeAndDate.between(from, to);
        assertEquals(from, result);
    }

    @Test
    public void testBetweenInvalidRange() {
        Instant from = Instant.now();
        Instant to = from.minusMillis(1000);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            timeAndDate.between(from, to);
        });

        String expectedMessage = "Invalid date range: the upper bound date (%s) is before the lower bound (%s)".formatted(to, from);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
