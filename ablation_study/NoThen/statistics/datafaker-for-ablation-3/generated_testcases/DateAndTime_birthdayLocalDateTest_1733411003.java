
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateAndTime_birthdayLocalDateTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        dateAndTime = new DateAndTime(faker);
    }

    @Test
    void testBirthdayLocalDateWithEqualAges() {
        int minAge = 30;
        int maxAge = 30;
        LocalDate result = dateAndTime.birthdayLocalDate(minAge, maxAge);
        LocalDate expected = LocalDate.now().minusYears(maxAge);
        assertTrue(result.isEqual(expected));
    }

    @Test
    void testBirthdayLocalDateWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        LocalDate result = dateAndTime.birthdayLocalDate(minAge, maxAge);
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        assertTrue(result.isAfter(from) || result.isEqual(from));
        assertTrue(result.isBefore(to) || result.isEqual(to));
    }

    @Test
    void testBirthdayLocalDateWithNegativeAges() {
        int minAge = -5;
        int maxAge = 5;
        LocalDate result = dateAndTime.birthdayLocalDate(minAge, maxAge);
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        assertTrue(result.isAfter(from) || result.isEqual(from));
        assertTrue(result.isBefore(to) || result.isEqual(to));
    }
}
