
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateAndTime_birthdayLocalDateTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        dateAndTime = new DateAndTime(new BaseFaker());
    }

    @Test
    public void testBirthdayLocalDateWithEqualAges() {
        int minAge = 30;
        int maxAge = 30;
        LocalDate result = dateAndTime.birthdayLocalDate(minAge, maxAge);
        LocalDate expected = LocalDate.now().minusYears(maxAge);
        assertEquals(expected, result);
    }

    @Test
    public void testBirthdayLocalDateWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        LocalDate result = dateAndTime.birthdayLocalDate(minAge, maxAge);
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        assertTrue(result.isAfter(from) || result.isEqual(from));
        assertTrue(result.isBefore(to) || result.isEqual(to));
    }
}
