
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class Time_betweenTest {

    @Test
    void testBetween_ValidRange() {
        Time time = new Time(new BaseProviders() {
            @Override
            public Number number() {
                return new Number() {
                    @Override
                    public long numberBetween(long min, long max) {
                        return min + 1; // Mocking a number between min and max
                    }
                };
            }
        });
        LocalTime from = LocalTime.of(10, 0);
        LocalTime to = LocalTime.of(12, 0);

        long result = time.between(from, to);

        assertTrue(result >= from.toNanoOfDay() && result < to.toNanoOfDay());
    }

    @Test
    void testBetween_EqualTimes() {
        Time time = new Time(new BaseProviders() {
            @Override
            public Number number() {
                return new Number() {
                    @Override
                    public long numberBetween(long min, long max) {
                        return min; // Mocking a number between min and max
                    }
                };
            }
        });
        LocalTime from = LocalTime.of(10, 0);
        LocalTime to = LocalTime.of(10, 0);

        long result = time.between(from, to);

        assertEquals(from.toNanoOfDay(), result);
    }

    @Test
    void testBetween_InvalidRange() {
        Time time = new Time(new BaseProviders() {
            @Override
            public Number number() {
                return new Number() {
                    @Override
                    public long numberBetween(long min, long max) {
                        return min; // Mocking a number between min and max
                    }
                };
            }
        });
        LocalTime from = LocalTime.of(12, 0);
        LocalTime to = LocalTime.of(10, 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            time.between(from, to);
        });

        String expectedMessage = "Invalid time range: the upper bound time (10:00) is before the lower bound (12:00)";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
