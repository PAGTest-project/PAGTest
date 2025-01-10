
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
        timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public void someMethod() {
                // Dummy implementation to allow instantiation
            }
        });
    }

    @Test
    void testBirthdayWithEqualAges() {
        int minAge = 25;
        int maxAge = 25;
        LocalDate expectedBirthday = LocalDate.now().minusYears(maxAge);
        assertEquals(expectedBirthday, timeAndDate.birthday(minAge, maxAge));
    }

    @Test
    void testBirthdayWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        LocalDate birthday = timeAndDate.birthday(minAge, maxAge);
        LocalDate minBirthday = LocalDate.now().minusYears(maxAge);
        LocalDate maxBirthday = LocalDate.now().minusYears(minAge);
        assertTrue(birthday.isAfter(minBirthday) || birthday.isEqual(minBirthday));
        assertTrue(birthday.isBefore(maxBirthday) || birthday.isEqual(maxBirthday));
    }
}
