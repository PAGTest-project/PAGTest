
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
        Instant from = Instant.parse("2023-01-01T00:00:00Z");
        Instant to = Instant.parse("2023-01-02T00:00:00Z");
        Instant result = timeAndDate.between(from, to);
        assertTrue(result.isAfter(from) || result.equals(from));
        assertTrue(result.isBefore(to) || result.equals(to));
    }

    @Test
    public void testBetweenEqualDates() {
        Instant from = Instant.parse("2023-01-01T00:00:00Z");
        Instant to = Instant.parse("2023-01-01T00:00:00Z");
        Instant result = timeAndDate.between(from, to);
        assertEquals(from, result);
    }

    @Test
    public void testBetweenInvalidRange() {
        Instant from = Instant.parse("2023-01-02T00:00:00Z");
        Instant to = Instant.parse("2023-01-01T00:00:00Z");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            timeAndDate.between(from, to);
        });
        assertEquals("Invalid date range: the upper bound date (2023-01-01T00:00:00Z) is before the lower bound (2023-01-02T00:00:00Z)", exception.getMessage());
    }
}
