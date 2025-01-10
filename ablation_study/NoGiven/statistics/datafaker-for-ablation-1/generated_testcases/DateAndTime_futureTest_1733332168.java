
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateAndTime_futureTest {

    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders();
        dateAndTime = new DateAndTime(baseProviders);
    }

    @Test
    public void testFutureWithDays() {
        int atMost = 10;
        TimeUnit unit = TimeUnit.DAYS;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp futureDate = dateAndTime.future(atMost, unit, referenceDate);

        long difference = futureDate.getTime() - referenceDate.getTime();
        assertTrue(difference > 0 && difference < unit.toMillis(atMost));
    }

    @Test
    public void testFutureWithHours() {
        int atMost = 24;
        TimeUnit unit = TimeUnit.HOURS;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp futureDate = dateAndTime.future(atMost, unit, referenceDate);

        long difference = futureDate.getTime() - referenceDate.getTime();
        assertTrue(difference > 0 && difference < unit.toMillis(atMost));
    }

    @Test
    public void testFutureWithMinutes() {
        int atMost = 60;
        TimeUnit unit = TimeUnit.MINUTES;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp futureDate = dateAndTime.future(atMost, unit, referenceDate);

        long difference = futureDate.getTime() - referenceDate.getTime();
        assertTrue(difference > 0 && difference < unit.toMillis(atMost));
    }
}
