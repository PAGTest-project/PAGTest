
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Time_pastTest {

    private Time time;

    @BeforeEach
    public void setup() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public Number number() {
                return new Number() {
                    @Override
                    public long numberBetween(long min, long max) {
                        return (long) (Math.random() * (max - min) + min);
                    }
                };
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation not needed for this test
            }
        };
        time = new Time(faker);
    }

    @Test
    public void testPast() {
        int atLeast = 1;
        ChronoUnit unit = ChronoUnit.HOURS;

        long result = time.past(atLeast, unit);

        LocalTime now = LocalTime.now();
        long atLeastTime = now.minus(atLeast, unit).toNanoOfDay();
        long timeNano = now.toNanoOfDay();

        assertTrue(result >= atLeastTime && result < timeNano);
    }
}
