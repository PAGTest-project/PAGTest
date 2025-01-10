
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
    void testGenerateValid() {
        IdNumberRequest request = new IdNumberRequest(0, 0, null);
        PersonIdNumber result = impl.generateValid(faker, request);

        assertNotNull(result);
        assertNotNull(result.getIdNumber());
        assertNotNull(result.getBirthday());
        assertNotNull(result.getGender());

        String basePart = impl.basePart(faker, result.getBirthday(), result.getGender());
        int expectedChecksum = impl.checksum(basePart);
        String expectedIdNumber = basePart + expectedChecksum;

        assertEquals(expectedIdNumber, result.getIdNumber());
    }

    @Test
    void testGenerateValidWithSpecificBirthdayAndGender() {
        LocalDate birthday = LocalDate.of(1990, 8, 19);
        Gender gender = Gender.MALE;
        IdNumberRequest request = new IdNumberRequest(0, 0, null);
        request.setBirthday(birthday);
        request.setGender(gender);

        PersonIdNumber result = impl.generateValid(faker, request);

        assertNotNull(result);
        assertNotNull(result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());

        String basePart = impl.basePart(faker, birthday, gender);
        int expectedChecksum = impl.checksum(basePart);
        String expectedIdNumber = basePart + expectedChecksum;

        assertEquals(expectedIdNumber, result.getIdNumber());
    }
}
