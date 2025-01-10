
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
        BaseProviders baseProviders = new BaseProviders();
        dateAndTime = new DateAndTime(baseProviders);
    }

    @Test
    public void testPast() {
        int atMost = 10;
        TimeUnit unit = TimeUnit.DAYS;
        Timestamp referenceDate = new Timestamp(System.currentTimeMillis());

        Timestamp pastDate = dateAndTime.past(atMost, unit, referenceDate);

        long upperBoundMillis = unit.toMillis(atMost);
        long referenceMillis = referenceDate.getTime();
        long pastMillis = pastDate.getTime();

        assertTrue(pastMillis < referenceMillis);
        assertTrue(referenceMillis - pastMillis <= upperBoundMillis);
    }
}
