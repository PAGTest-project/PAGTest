
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeAndDate_birthdayTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders());
    }

    @Test
    public void testBirthdayWithEqualAges() {
        LocalDate result = timeAndDate.birthday(30, 30);
        LocalDate expected = LocalDate.now().minusYears(30);
        assertEquals(expected, result);
    }

    @Test
    public void testBirthdayWithDifferentAges() {
        LocalDate result = timeAndDate.birthday(18, 65);
        LocalDate minExpected = LocalDate.now().minusYears(65);
        LocalDate maxExpected = LocalDate.now().minusYears(18);
        assertTrue(result.isAfter(minExpected) || result.isEqual(minExpected));
        assertTrue(result.isBefore(maxExpected) || result.isEqual(maxExpected));
    }
}
