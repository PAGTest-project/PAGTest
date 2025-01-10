
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeAndDate_futureTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public Number number() {
                return new Number();
            }
        });
    }

    @Test
    public void testFutureDateWithinRange() {
        long atMost = 1000; // 1 second
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();

        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(atMost));
    }

    @Test
    public void testFutureDateWithLargeRange() {
        long atMost = TimeUnit.DAYS.toMillis(365); // 1 year
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();

        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(atMost));
    }

    @Test
    public void testFutureDateWithMinimalRange() {
        long atMost = 1; // 1 millisecond
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();

        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(atMost));
    }
}
