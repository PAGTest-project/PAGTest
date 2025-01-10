
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
    void testBetweenValidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis() - 86400000); // 1 day ago
        Timestamp to = new Timestamp(System.currentTimeMillis() + 86400000); // 1 day from now

        Timestamp result = dateAndTime.between(from, to);

        assertTrue(result.after(from) || result.equals(from));
        assertTrue(result.before(to) || result.equals(to));
    }

    @Test
    void testBetweenEqualDates() {
        Timestamp from = new Timestamp(System.currentTimeMillis());
        Timestamp to = new Timestamp(System.currentTimeMillis());

        Timestamp result = dateAndTime.between(from, to);

        assertEquals(from, result);
    }

    @Test
    void testBetweenInvalidRange() {
        Timestamp from = new Timestamp(System.currentTimeMillis() + 86400000); // 1 day from now
        Timestamp to = new Timestamp(System.currentTimeMillis() - 86400000); // 1 day ago

        assertThrows(IllegalArgumentException.class, () -> {
            dateAndTime.between(from, to);
        });
    }
}
