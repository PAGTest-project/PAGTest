
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class DateAndTime_betweenTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        dateAndTime = new DateAndTime(new BaseFaker());
    }

    @Test
    public void testBetweenValidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis());
        Timestamp to = new Timestamp(from.getTime() + 10000); // 10 seconds later

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
        Timestamp from = new Timestamp(System.currentTimeMillis() + 10000); // 10 seconds later
        Timestamp to = new Timestamp(System.currentTimeMillis());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            dateAndTime.between(from, to);
        });

        String expectedMessage = "Invalid date range: the upper bound date (" + to + ") is before the lower bound (" + from + ")";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
