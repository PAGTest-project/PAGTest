
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

public class TimeAndDate_betweenTest {

    @Test
    void testBetween_ValidRange() {
        TimeAndDate timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
        Instant from = Instant.parse("2023-01-01T00:00:00Z");
        Instant to = Instant.parse("2023-01-02T00:00:00Z");

        Instant result = timeAndDate.between(from, to);

        assertTrue(result.isAfter(from) || result.equals(from));
        assertTrue(result.isBefore(to));
    }

    @Test
    void testBetween_EqualDates() {
        TimeAndDate timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
        Instant from = Instant.parse("2023-01-01T00:00:00Z");
        Instant to = Instant.parse("2023-01-01T00:00:00Z");

        Instant result = timeAndDate.between(from, to);

        assertEquals(from, result);
    }

    @Test
    void testBetween_InvalidRange() {
        TimeAndDate timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
        Instant from = Instant.parse("2023-01-02T00:00:00Z");
        Instant to = Instant.parse("2023-01-01T00:00:00Z");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            timeAndDate.between(from, to);
        });

        String expectedMessage = "Invalid date range: the upper bound date (2023-01-01T00:00:00Z) is before the lower bound (2023-01-02T00:00:00Z)";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
