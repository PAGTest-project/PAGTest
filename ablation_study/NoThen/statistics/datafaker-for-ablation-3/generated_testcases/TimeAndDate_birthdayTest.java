
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeAndDate_birthdayTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders() {});
    }

    @Test
    public void testBirthdayWithEqualAges() {
        int minAge = 30;
        int maxAge = 30;
        LocalDate expectedBirthday = LocalDate.now().minusYears(maxAge);
        LocalDate actualBirthday = timeAndDate.birthday(minAge, maxAge);
        assertEquals(expectedBirthday, actualBirthday);
    }

    @Test
    public void testBirthdayWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        LocalDate actualBirthday = timeAndDate.birthday(minAge, maxAge);
        LocalDate minBirthday = LocalDate.now().minusYears(minAge);
        LocalDate maxBirthday = LocalDate.now().minusYears(maxAge);
        assertTrue(actualBirthday.isAfter(maxBirthday) || actualBirthday.isEqual(maxBirthday));
        assertTrue(actualBirthday.isBefore(minBirthday) || actualBirthday.isEqual(minBirthday));
    }

    @Test
    public void testBirthdayWithNegativeAges() {
        int minAge = -5;
        int maxAge = -1;
        LocalDate actualBirthday = timeAndDate.birthday(minAge, maxAge);
        LocalDate minBirthday = LocalDate.now().minusYears(minAge);
        LocalDate maxBirthday = LocalDate.now().minusYears(maxAge);
        assertTrue(actualBirthday.isAfter(maxBirthday) || actualBirthday.isEqual(maxBirthday));
        assertTrue(actualBirthday.isBefore(minBirthday) || actualBirthday.isEqual(minBirthday));
    }
}
