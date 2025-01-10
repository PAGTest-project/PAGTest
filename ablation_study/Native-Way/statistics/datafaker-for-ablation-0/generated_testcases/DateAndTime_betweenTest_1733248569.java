
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class DateAndTime_betweenTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        dateAndTime = new DateAndTime(new BaseProviders());
    }

    @Test
    public void testBetweenValidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis());
        Timestamp to = new Timestamp(from.getTime() + 10000);
        Timestamp result = dateAndTime.between(from, to);
        assertTrue(result.getTime() >= from.getTime() && result.getTime() < to.getTime());
    }

    @Test
    public void testBetweenEqualDates() {
        Timestamp from = new Timestamp(System.currentTimeMillis());
        Timestamp to = new Timestamp(from.getTime());
        Timestamp result = dateAndTime.between(from, to);
        assertEquals(from, result);
    }

    @Test
    public void testBetweenInvalidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis() + 10000);
        Timestamp to = new Timestamp(System.currentTimeMillis());
        assertThrows(IllegalArgumentException.class, () -> {
            dateAndTime.between(from, to);
        });
    }
}
