
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateAndTime_pastTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        dateAndTime = new DateAndTime(faker);
    }

    @Test
    void testPastWithDays() {
        int atMost = 10;
        TimeUnit unit = TimeUnit.DAYS;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp pastDate = dateAndTime.past(atMost, unit, referenceDate);

        assertTrue(pastDate.before(referenceDate));
        assertTrue(referenceDate.getTime() - pastDate.getTime() < unit.toMillis(atMost));
    }

    @Test
    void testPastWithHours() {
        int atMost = 24;
        TimeUnit unit = TimeUnit.HOURS;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp pastDate = dateAndTime.past(atMost, unit, referenceDate);

        assertTrue(pastDate.before(referenceDate));
        assertTrue(referenceDate.getTime() - pastDate.getTime() < unit.toMillis(atMost));
    }

    @Test
    void testPastWithMinutes() {
        int atMost = 60;
        TimeUnit unit = TimeUnit.MINUTES;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp pastDate = dateAndTime.past(atMost, unit, referenceDate);

        assertTrue(pastDate.before(referenceDate));
        assertTrue(referenceDate.getTime() - pastDate.getTime() < unit.toMillis(atMost));
    }
}
