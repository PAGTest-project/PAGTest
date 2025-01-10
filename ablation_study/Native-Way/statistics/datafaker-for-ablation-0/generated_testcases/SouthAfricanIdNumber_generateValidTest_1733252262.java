
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseFaker;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SouthAfricanIdNumber_generateValidTest {

    private SouthAfricanIdNumber idNumberGenerator;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        idNumberGenerator = new SouthAfricanIdNumber();
        faker = new BaseFaker();
    }

    @Test
    public void testGenerateValidWithFemale() {
        IdNumberRequest request = new IdNumberRequest() {
            @Override
            public LocalDate getBirthDate() {
                return LocalDate.of(1990, 1, 1);
            }

            @Override
            public Gender getGender() {
                return Gender.FEMALE;
            }
        };

        PersonIdNumber result = idNumberGenerator.generateValid(faker, request);
        assertNotNull(result);
        assertEquals(Gender.FEMALE, result.getGender());
        assertEquals(LocalDate.of(1990, 1, 1), result.getBirthDate());
    }

    @Test
    public void testGenerateValidWithMale() {
        IdNumberRequest request = new IdNumberRequest() {
            @Override
            public LocalDate getBirthDate() {
                return LocalDate.of(1985, 12, 31);
            }

            @Override
            public Gender getGender() {
                return Gender.MALE;
            }
        };

        PersonIdNumber result = idNumberGenerator.generateValid(faker, request);
        assertNotNull(result);
        assertEquals(Gender.MALE, result.getGender());
        assertEquals(LocalDate.of(1985, 12, 31), result.getBirthDate());
    }

    @Test
    public void testGenerateValidChecksum() {
        IdNumberRequest request = new IdNumberRequest() {
            @Override
            public LocalDate getBirthDate() {
                return LocalDate.of(1995, 6, 15);
            }

            @Override
            public Gender getGender() {
                return Gender.FEMALE;
            }
        };

        PersonIdNumber result = idNumberGenerator.generateValid(faker, request);
        assertNotNull(result);
        String basePart = SouthAfricanIdNumber.DATE_TIME_FORMATTER.format(request.getBirthDate())
                + SouthAfricanIdNumber.sequentialNumber(faker, request.getGender())
                + faker.options().option(SouthAfricanIdNumber.CODE_PATTERN);
        int expectedChecksum = SouthAfricanIdNumber.calculateChecksum(basePart, 12);
        int actualChecksum = Integer.parseInt(result.getIdNumber().substring(12));
        assertEquals(expectedChecksum, actualChecksum);
    }
}
