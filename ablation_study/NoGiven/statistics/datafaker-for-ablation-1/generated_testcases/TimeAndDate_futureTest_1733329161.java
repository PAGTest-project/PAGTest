
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeAndDate_futureTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders());
    }

    @Test
    public void testFutureWithReferenceDate() {
        Instant referenceDate = Instant.now();
        long atMost = 1000;
        TimeUnit unit = TimeUnit.MILLISECONDS;

        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertTrue(futureDate.isAfter(referenceDate));
        assertTrue(futureDate.toEpochMilli() < referenceDate.toEpochMilli() + atMost);
    }

    @Test
    public void testFutureWithLargeTimeUnit() {
        Instant referenceDate = Instant.now();
        long atMost = 1;
        TimeUnit unit = TimeUnit.DAYS;

        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertTrue(futureDate.isAfter(referenceDate));
        assertTrue(futureDate.toEpochMilli() < referenceDate.toEpochMilli() + TimeUnit.DAYS.toMillis(atMost));
    }

    @Test
    public void testFutureWithSmallTimeUnit() {
        Instant referenceDate = Instant.now();
        long atMost = 1000;
        TimeUnit unit = TimeUnit.MICROSECONDS;

        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertTrue(futureDate.isAfter(referenceDate));
        assertTrue(futureDate.toEpochMilli() < referenceDate.toEpochMilli() + TimeUnit.MICROSECONDS.toMillis(atMost));
    }
}
