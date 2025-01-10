
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

public class AlbanianIdNumber_generateValidTest {
    private AlbanianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new AlbanianIdNumber();
        faker = new Faker();
    }

    @Test
    public void testGenerateValid() {
        IdNumberRequest request = new IdNumberRequest();
        request.setBirthDate(LocalDate.of(1990, 5, 15));
        request.setGender(Gender.MALE);

        PersonIdNumber idNumber = generator.generateValid(faker, request);

        assertNotNull(idNumber);
        assertEquals(LocalDate.of(1990, 5, 15), idNumber.getBirthDate());
        assertEquals(Gender.MALE, idNumber.getGender());
        assertNotNull(idNumber.getIdNumber());
    }

    @Test
    public void testGenerateValidFemale() {
        IdNumberRequest request = new IdNumberRequest();
        request.setBirthDate(LocalDate.of(1985, 10, 20));
        request.setGender(Gender.FEMALE);

        PersonIdNumber idNumber = generator.generateValid(faker, request);

        assertNotNull(idNumber);
        assertEquals(LocalDate.of(1985, 10, 20), idNumber.getBirthDate());
        assertEquals(Gender.FEMALE, idNumber.getGender());
        assertNotNull(idNumber.getIdNumber());
    }

    @Test
    public void testGenerateValidChecksum() {
        IdNumberRequest request = new IdNumberRequest();
        request.setBirthDate(LocalDate.of(1975, 3, 25));
        request.setGender(Gender.MALE);

        PersonIdNumber idNumber = generator.generateValid(faker, request);

        assertNotNull(idNumber);
        String basePart = idNumber.getIdNumber().substring(0, idNumber.getIdNumber().length() - 1);
        char expectedChecksum = generator.checksum(basePart);
        assertEquals(expectedChecksum, idNumber.getIdNumber().charAt(idNumber.getIdNumber().length() - 1));
    }
}
