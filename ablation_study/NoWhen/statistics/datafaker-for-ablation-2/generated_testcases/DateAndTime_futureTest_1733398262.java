
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
        dateAndTime = new DateAndTime(new BaseProviders());
    }

    @Test
    void testFutureDateWithinBounds() {
        int atMost = 100;
        TimeUnit unit = TimeUnit.SECONDS;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp futureDate = dateAndTime.future(atMost, unit, referenceDate);

        long referenceMillis = referenceDate.getTime();
        long futureMillis = futureDate.getTime();
        long upperBoundMillis = referenceMillis + unit.toMillis(atMost);

        assertTrue(futureMillis > referenceMillis);
        assertTrue(futureMillis < upperBoundMillis);
    }

    @Test
    void testFutureDateWithMinimumTime() {
        int atMost = 100;
        int minimum = 50;
        TimeUnit unit = TimeUnit.SECONDS;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp futureDate = dateAndTime.future(atMost, minimum, unit);

        long referenceMillis = referenceDate.getTime();
        long futureMillis = futureDate.getTime();
        long minimumMillis = referenceMillis + unit.toMillis(minimum);
        long upperBoundMillis = referenceMillis + unit.toMillis(atMost);

        assertTrue(futureMillis > minimumMillis);
        assertTrue(futureMillis < upperBoundMillis);
    }
}
