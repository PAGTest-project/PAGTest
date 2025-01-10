
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
        timeAndDate = new TimeAndDate(new BaseProviders());
    }

    @Test
    void testBirthdayWithEqualAges() {
        int minAge = 30;
        int maxAge = 30;
        LocalDate expectedBirthday = LocalDate.now().minusYears(maxAge);

        LocalDate result = timeAndDate.birthday(minAge, maxAge);

        assertEquals(expectedBirthday, result);
    }

    @Test
    void testBirthdayWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);

        LocalDate result = timeAndDate.birthday(minAge, maxAge);

        assertTrue(result.isAfter(from) || result.isEqual(from));
        assertTrue(result.isBefore(to) || result.isEqual(to));
    }

    @Test
    void testBirthdayWithNegativeAges() {
        int minAge = -5;
        int maxAge = -1;
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);

        LocalDate result = timeAndDate.birthday(minAge, maxAge);

        assertTrue(result.isAfter(from) || result.isEqual(from));
        assertTrue(result.isBefore(to) || result.isEqual(to));
    }
}
