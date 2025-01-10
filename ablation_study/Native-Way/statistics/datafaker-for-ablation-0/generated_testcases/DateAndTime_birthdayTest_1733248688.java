
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateAndTime_birthdayTest {
    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        dateAndTime = new DateAndTime(new BaseProviders());
    }

    @Test
    public void testBirthdayWithEqualAges() {
        int minAge = 30;
        int maxAge = 30;
        Timestamp birthday = dateAndTime.birthday(minAge, maxAge);
        LocalDateTime expectedDateTime = LocalDateTime.of(LocalDate.now().minusYears(maxAge), LocalTime.MIDNIGHT);
        assertEquals(Timestamp.valueOf(expectedDateTime), birthday);
    }

    @Test
    public void testBirthdayWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        Timestamp birthday = dateAndTime.birthday(minAge, maxAge);
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        assertTrue(birthday.toLocalDateTime().toLocalDate().isAfter(from) || birthday.toLocalDateTime().toLocalDate().isEqual(from));
        assertTrue(birthday.toLocalDateTime().toLocalDate().isBefore(to) || birthday.toLocalDateTime().toLocalDate().isEqual(to));
    }

    @Test
    public void testBirthdayWithNegativeAges() {
        int minAge = -5;
        int maxAge = -1;
        Timestamp birthday = dateAndTime.birthday(minAge, maxAge);
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        assertTrue(birthday.toLocalDateTime().toLocalDate().isAfter(from) || birthday.toLocalDateTime().toLocalDate().isEqual(from));
        assertTrue(birthday.toLocalDateTime().toLocalDate().isBefore(to) || birthday.toLocalDateTime().toLocalDate().isEqual(to));
    }
}
