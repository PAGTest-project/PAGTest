
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
            public RandomService random() {
                return new RandomService();
            }
        });
    }

    @Test
    void testFutureWithReferenceDate() {
        long atMost = 1000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Instant referenceDate = Instant.now();

        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(atMost));
    }
}
