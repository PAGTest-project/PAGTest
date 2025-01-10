
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class DateAndTime_betweenTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker();
        dateAndTime = new DateAndTime(faker);
    }

    @Test
    public void testBetweenValidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis() - 1000000);
        Timestamp to = new Timestamp(System.currentTimeMillis() + 1000000);

        Timestamp result = dateAndTime.between(from, to);

        assertTrue(result.after(from) || result.equals(from));
        assertTrue(result.before(to) || result.equals(to));
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
        Timestamp from = new Timestamp(System.currentTimeMillis() + 1000000);
        Timestamp to = new Timestamp(System.currentTimeMillis() - 1000000);

        assertThrows(IllegalArgumentException.class, () -> {
            dateAndTime.between(from, to);
        });
    }
}
