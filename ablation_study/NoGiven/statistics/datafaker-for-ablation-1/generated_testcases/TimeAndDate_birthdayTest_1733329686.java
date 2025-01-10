
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeAndDate_birthdayTest {
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        timeAndDate = new TimeAndDate(new BaseProviders() {
            @Override
            public void setupProvider() {
                // Dummy implementation
            }
        });
    }

    @Test
    public void testBirthdayWithEqualAges() {
        int minAge = 30;
        int maxAge = 30;
        LocalDate expectedBirthday = LocalDate.now().minusYears(maxAge);
        assertEquals(expectedBirthday, timeAndDate.birthday(minAge, maxAge));
    }

    @Test
    public void testBirthdayWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        LocalDate birthday = timeAndDate.birthday(minAge, maxAge);
        LocalDate minBirthday = LocalDate.now().minusYears(maxAge);
        LocalDate maxBirthday = LocalDate.now().minusYears(minAge);
        assert(birthday.isAfter(minBirthday) || birthday.isEqual(minBirthday));
        assert(birthday.isBefore(maxBirthday) || birthday.isEqual(maxBirthday));
    }
}
