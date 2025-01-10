
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static net.datafaker.providers.base.PersonIdNumber.Gender.FEMALE;
import static net.datafaker.providers.base.PersonIdNumber.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanianIdNumber_generateInvalidTest {
    private RomanianIdNumber impl;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        impl = new RomanianIdNumber();
        faker = new Faker();
    }

    @Test
    void testGenerateInvalid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = MALE;
        String basePart = impl.basePart(faker, birthday, gender);
        int expectedChecksum = (impl.checksum(basePart) + 1) % 10;
        String expectedInvalidId = basePart + expectedChecksum;

        String actualInvalidId = impl.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }

    @Test
    void testGenerateInvalidWithFemale() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = FEMALE;
        String basePart = impl.basePart(faker, birthday, gender);
        int expectedChecksum = (impl.checksum(basePart) + 1) % 10;
        String expectedInvalidId = basePart + expectedChecksum;

        String actualInvalidId = impl.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }

    @Test
    void testGenerateInvalidWithDifferentBirthday() {
        LocalDate birthday = LocalDate.of(1985, 12, 31);
        Gender gender = MALE;
        String basePart = impl.basePart(faker, birthday, gender);
        int expectedChecksum = (impl.checksum(basePart) + 1) % 10;
        String expectedInvalidId = basePart + expectedChecksum;

        String actualInvalidId = impl.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }
}
