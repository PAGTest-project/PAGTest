
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class DateAndTime_betweenTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        dateAndTime = new DateAndTime(faker);
    }

    @Test
    public void testBetweenValidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1));
        Timestamp to = new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));

        Timestamp result = dateAndTime.between(from, to);

        assertTrue(result.getTime() >= from.getTime() && result.getTime() < to.getTime());
    }

    @Test
    public void testBetweenEqualDates() {
        Timestamp from = new Timestamp(System.currentTimeMillis());
        Timestamp to = new Timestamp(System.currentTimeMillis());

        Timestamp result = dateAndTime.between(from, to);

        assertEquals(from, result);
    }

    @Test
    public void testBetweenInvalidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));
        Timestamp to = new Timestamp(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1));

        assertThrows(IllegalArgumentException.class, () -> {
            dateAndTime.between(from, to);
        });
    }

    @Test
    public void testBetweenWithBirthdayDates() {
        Timestamp from = dateAndTime.birthday(18, 65);
        Timestamp to = new Timestamp(System.currentTimeMillis());

        Timestamp result = dateAndTime.between(from, to);

        assertTrue(result.getTime() >= from.getTime() && result.getTime() < to.getTime());
    }

    @Test
    public void testBetweenWithFutureDates() {
        Timestamp from = new Timestamp(System.currentTimeMillis());
        Timestamp to = dateAndTime.future(10, TimeUnit.DAYS);

        Timestamp result = dateAndTime.between(from, to);

        assertTrue(result.getTime() >= from.getTime() && result.getTime() < to.getTime());
    }

    @Test
    public void testBetweenWithPastDates() {
        Timestamp from = dateAndTime.past(10, TimeUnit.DAYS);
        Timestamp to = new Timestamp(System.currentTimeMillis());

        Timestamp result = dateAndTime.between(from, to);

        assertTrue(result.getTime() >= from.getTime() && result.getTime() < to.getTime());
    }
}
