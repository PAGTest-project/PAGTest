
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
    void futureTestWithSeconds() {
        long atMost = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        Instant referenceDate = Instant.now();
        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(unit.toMillis(atMost)));
    }

    @Test
    void futureTestWithMinutes() {
        long atMost = 5;
        TimeUnit unit = TimeUnit.MINUTES;
        Instant referenceDate = Instant.now();
        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(unit.toMillis(atMost)));
    }

    @Test
    void futureTestWithHours() {
        long atMost = 2;
        TimeUnit unit = TimeUnit.HOURS;
        Instant referenceDate = Instant.now();
        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(unit.toMillis(atMost)));
    }

    @Test
    void futureTestWithDays() {
        long atMost = 1;
        TimeUnit unit = TimeUnit.DAYS;
        Instant referenceDate = Instant.now();
        Instant futureDate = timeAndDate.future(atMost, unit, referenceDate);

        assertThat(futureDate).isAfterOrEqualTo(referenceDate.plusMillis(1));
        assertThat(futureDate).isBefore(referenceDate.plusMillis(unit.toMillis(atMost)));
    }
}
