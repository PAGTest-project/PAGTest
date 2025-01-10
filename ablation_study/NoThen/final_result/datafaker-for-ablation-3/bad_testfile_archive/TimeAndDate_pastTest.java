
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeAndDate_pastTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        });
    }

    @Test
    public void testPastWithinRange() {
        long atMost = 1000; // 1 second
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(atMost, unit, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
        assertTrue(referenceDate.toEpochMilli() - pastDate.toEpochMilli() < atMost);
    }

    @Test
    public void testPastWithLargeRange() {
        long atMost = TimeUnit.DAYS.toMillis(365); // 1 year
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(atMost, unit, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
        assertTrue(referenceDate.toEpochMilli() - pastDate.toEpochMilli() < atMost);
    }

    @Test
    public void testPastWithSmallRange() {
        long atMost = 1; // 1 millisecond
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(atMost, unit, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
        assertTrue(referenceDate.toEpochMilli() - pastDate.toEpochMilli() < atMost);
    }
}
