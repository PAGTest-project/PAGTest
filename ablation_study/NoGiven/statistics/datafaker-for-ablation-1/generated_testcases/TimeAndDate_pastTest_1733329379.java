
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeAndDate_pastTest {

    @Test
    public void testPast() {
        // Given
        long atMost = 1000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();
        TimeAndDate timeAndDate = new TimeAndDate(null); // Faker instance not needed for this test

        // When
        Instant result = timeAndDate.past(atMost, unit, referenceDate);

        // Then
        long upperBoundMillis = unit.toMillis(atMost);
        long pastMillis = referenceDate.toEpochMilli() - 1 - upperBoundMillis + 1;
        long futureMillis = referenceDate.toEpochMilli() - 1;
        assertTrue(result.toEpochMilli() >= pastMillis && result.toEpochMilli() < futureMillis);
    }
}
