
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;
import net.datafaker.service.RandomService;

public class TimeAndDate_pastTest {

    @Test
    public void testPast() {
        // Given
        long atMost = 1000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();
        TimeAndDate timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public long nextLong(long bound) {
                        return bound / 2; // Mock random value
                    }
                };
            }
        });

        // When
        Instant result = timeAndDate.past(atMost, unit, referenceDate);

        // Then
        long upperBoundMillis = unit.toMillis(atMost);
        long pastMillis = referenceDate.toEpochMilli() - 1 - upperBoundMillis + 1;
        long futureMillis = referenceDate.toEpochMilli() - 1;
        assertTrue(result.toEpochMilli() >= pastMillis && result.toEpochMilli() < futureMillis);
    }
}
