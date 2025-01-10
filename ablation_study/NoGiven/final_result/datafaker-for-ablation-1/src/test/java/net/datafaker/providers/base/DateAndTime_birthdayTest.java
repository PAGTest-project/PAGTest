
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateAndTime_birthdayTest {

    private DateAndTime dateAndTime;

    @BeforeEach
    public void setUp() {
        BaseFaker baseFaker = new BaseFaker();
        dateAndTime = new DateAndTime(baseFaker);
    }

    @Test
    public void testBirthdayWithEqualAges() {
        int minAge = 30;
        int maxAge = 30;
        Timestamp result = dateAndTime.birthday(minAge, maxAge);
        LocalDateTime expectedDateTime = LocalDateTime.of(LocalDate.now().minusYears(maxAge), LocalTime.MIDNIGHT);
        Timestamp expectedTimestamp = Timestamp.valueOf(expectedDateTime);
        assertTrue(result.equals(expectedTimestamp));
    }

    @Test
    public void testBirthdayWithDifferentAges() {
        int minAge = 18;
        int maxAge = 65;
        Timestamp result = dateAndTime.birthday(minAge, maxAge);
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        LocalDate resultDate = result.toLocalDateTime().toLocalDate();
        assertTrue(resultDate.isAfter(from) || resultDate.isEqual(from));
        assertTrue(resultDate.isBefore(to) || resultDate.isEqual(to));
    }

    @Test
    public void testBirthdayWithNegativeAges() {
        int minAge = -5;
        int maxAge = -1;
        Timestamp result = dateAndTime.birthday(minAge, maxAge);
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        LocalDate resultDate = result.toLocalDateTime().toLocalDate();
        assertTrue(resultDate.isAfter(from) || resultDate.isEqual(from));
        assertTrue(resultDate.isBefore(to) || resultDate.isEqual(to));
    }
}
