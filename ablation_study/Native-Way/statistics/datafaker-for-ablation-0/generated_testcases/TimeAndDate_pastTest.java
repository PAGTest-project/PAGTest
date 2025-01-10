
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    public void testPastDateWithinRange() {
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(1000, TimeUnit.MILLISECONDS, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
    }

    @Test
    public void testPastDateAtBoundary() {
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(1, TimeUnit.MILLISECONDS, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
    }

    @Test
    public void testPastDateWithZeroAtMost() {
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(0, TimeUnit.MILLISECONDS, referenceDate);
        assertFalse(pastDate.isBefore(referenceDate));
    }

    @Test
    public void testPastDateWithLargeAtMost() {
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(1000000, TimeUnit.MILLISECONDS, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
    }
}
