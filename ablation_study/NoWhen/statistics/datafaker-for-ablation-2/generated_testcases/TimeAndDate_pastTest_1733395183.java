
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
        timeAndDate = new TimeAndDate(new BaseProviders());
    }

    @Test
    public void testPastWithSeconds() {
        long atMost = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(atMost, unit, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
    }

    @Test
    public void testPastWithMinutes() {
        long atMost = 5;
        TimeUnit unit = TimeUnit.MINUTES;
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(atMost, unit, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
    }

    @Test
    public void testPastWithHours() {
        long atMost = 2;
        TimeUnit unit = TimeUnit.HOURS;
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(atMost, unit, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
    }

    @Test
    public void testPastWithDays() {
        long atMost = 1;
        TimeUnit unit = TimeUnit.DAYS;
        Instant referenceDate = Instant.now();
        Instant pastDate = timeAndDate.past(atMost, unit, referenceDate);
        assertTrue(pastDate.isBefore(referenceDate));
    }
}
