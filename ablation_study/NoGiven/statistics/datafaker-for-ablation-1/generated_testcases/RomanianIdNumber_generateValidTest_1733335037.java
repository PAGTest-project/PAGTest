
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RomanianIdNumber_generateValidTest {
    private RomanianIdNumber impl;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        impl = new RomanianIdNumber();
        faker = new Faker();
    }

    @Test
    public void testGenerateValid() {
        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber idNumber = impl.generateValid(faker, request);

        assertNotNull(idNumber);
        assertNotNull(idNumber.getIdNumber());
        assertNotNull(idNumber.getBirthday());
        assertNotNull(idNumber.getGender());

        String basePart = idNumber.getIdNumber().substring(0, 12);
        int expectedChecksum = impl.checksum(basePart);
        int actualChecksum = Character.getNumericValue(idNumber.getIdNumber().charAt(12));

        assertEquals(expectedChecksum, actualChecksum);
    }

    @Test
    public void testGenerateValidWithSpecificBirthdayAndGender() {
        IdNumberRequest request = new IdNumberRequest();
        LocalDate birthday = LocalDate.of(1990, 8, 19);
        Gender gender = Gender.MALE;

        PersonIdNumber idNumber = impl.generateValid(faker, request);

        assertNotNull(idNumber);
        assertEquals(birthday, idNumber.getBirthday());
        assertEquals(gender, idNumber.getGender());

        String basePart = idNumber.getIdNumber().substring(0, 12);
        int expectedChecksum = impl.checksum(basePart);
        int actualChecksum = Character.getNumericValue(idNumber.getIdNumber().charAt(12));

        assertEquals(expectedChecksum, actualChecksum);
    }

    @Test
    public void testGenerateInvalid() {
        String invalidIdNumber = impl.generateInvalid(faker);

        assertNotNull(invalidIdNumber);

        String basePart = invalidIdNumber.substring(0, 12);
        int expectedChecksum = impl.checksum(basePart);
        int actualChecksum = Character.getNumericValue(invalidIdNumber.charAt(12));

        assertEquals((expectedChecksum + 1) % 10, actualChecksum);
    }
}
