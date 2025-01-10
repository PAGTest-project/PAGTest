
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class DateAndTime_futureTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker();
        dateAndTime = new DateAndTime(faker);
    }

    @Test
    void testFutureDateWithReferenceDate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp futureDate = dateAndTime.future(1, TimeUnit.SECONDS, now);
        assertThat(futureDate.getTime()).isGreaterThan(now.getTime())
                .isLessThan(now.getTime() + 1000);
    }

    @Test
    void testFutureDateWithMinimumTime() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp futureDate = dateAndTime.future(2, 1, TimeUnit.SECONDS);
        assertThat(futureDate.getTime()).isGreaterThan(now.getTime() + 1000)
                .isLessThan(now.getTime() + 2000);
    }

    @Test
    void testFutureDateToString() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String futureDateString = dateAndTime.future(1, TimeUnit.SECONDS, now, "yyyy-MM-dd HH:mm:ss");
        Timestamp futureDate = Timestamp.valueOf(futureDateString);
        assertThat(futureDate.getTime()).isGreaterThan(now.getTime())
                .isLessThan(now.getTime() + 1000);
    }

    @Test
    void testFutureDateBetweenRange() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp futureDate = dateAndTime.future(2, TimeUnit.SECONDS, now);
        Timestamp minDate = new Timestamp(now.getTime() + 1);
        Timestamp maxDate = new Timestamp(now.getTime() + 2000);
        assertThat(futureDate).isBetween(minDate, maxDate);
    }
}
