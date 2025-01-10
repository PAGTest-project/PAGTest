
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
    void testGenerateValid() {
        IdNumberRequest request = new IdNumberRequest(1990, 5, Gender.MALE);

        PersonIdNumber idNumber = generator.generateValid(faker, request);

        assertNotNull(idNumber);
        assertEquals(LocalDate.of(1990, 5, 15), idNumber.getBirthDate());
        assertEquals(Gender.MALE, idNumber.getGender());
    }

    @Test
    void testGenerateValidFemale() {
        IdNumberRequest request = new IdNumberRequest(1985, 10, Gender.FEMALE);

        PersonIdNumber idNumber = generator.generateValid(faker, request);

        assertNotNull(idNumber);
        assertEquals(LocalDate.of(1985, 10, 20), idNumber.getBirthDate());
        assertEquals(Gender.FEMALE, idNumber.getGender());
    }

    @Test
    void testGenerateValidChecksum() {
        IdNumberRequest request = new IdNumberRequest(1995, 3, Gender.MALE);

        PersonIdNumber idNumber = generator.generateValid(faker, request);

        assertNotNull(idNumber);
        String basePart = generator.yy(1995) + generator.mm(3, Gender.MALE) + generator.dd(25) + generator.sss(faker);
        char expectedChecksum = generator.checksum(basePart);
        assertEquals(expectedChecksum, idNumber.getIdNumber().charAt(idNumber.getIdNumber().length() - 1));
    }
}
