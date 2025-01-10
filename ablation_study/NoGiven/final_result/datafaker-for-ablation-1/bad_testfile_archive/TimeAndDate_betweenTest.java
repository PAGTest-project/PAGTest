
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
    void testBetweenValidRange() {
        Instant from = Instant.now();
        Instant to = from.plusMillis(1000);

        Instant result = timeAndDate.between(from, to);

        assertTrue(result.isAfter(from) || result.equals(from));
        assertTrue(result.isBefore(to));
    }

    @Test
    void testBetweenEqualDates() {
        Instant from = Instant.now();
        Instant to = from;

        Instant result = timeAndDate.between(from, to);

        assertEquals(from, result);
    }

    @Test
    void testBetweenInvalidRange() {
        Instant from = Instant.now();
        Instant to = from.minusMillis(1000);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            timeAndDate.between(from, to);
        });

        String expectedMessage = "Invalid date range: the upper bound date (" + to + ") is before the lower bound (" + from + ")";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
