
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseFaker;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static net.datafaker.idnumbers.SouthAfricanIdNumber.isValidEnZASsn;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SouthAfricanIdNumber_generateValidTest {

    private SouthAfricanIdNumber idNumberGenerator;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        idNumberGenerator = new SouthAfricanIdNumber();
        faker = new BaseFaker();
    }

    @Test
    public void testGenerateValidIdNumber() {
        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber idNumber = idNumberGenerator.generateValid(faker, request);
        assertTrue(isValidEnZASsn(idNumber.getIdNumber()));
    }

    @Test
    public void testGenerateValidIdNumberWithSpecificBirthdayAndGender() {
        IdNumberRequest request = new IdNumberRequest();
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        request.setBirthday(birthday);
        request.setGender(Gender.FEMALE);

        PersonIdNumber idNumber = idNumberGenerator.generateValid(faker, request);
        String basePart = DateTimeFormatter.ofPattern("yyMMdd").format(birthday)
                + sequentialNumber(faker, Gender.FEMALE)
                + faker.options().option(SouthAfricanIdNumber.CODE_PATTERN);
        String expectedIdNumber = basePart + idNumberGenerator.calculateChecksum(basePart, 12);

        assertTrue(isValidEnZASsn(idNumber.getIdNumber()));
        assertTrue(idNumber.getIdNumber().startsWith(expectedIdNumber.substring(0, 12)));
    }

    @Test
    public void testGenerateInvalidIdNumber() {
        String invalidIdNumber = idNumberGenerator.generateInvalid(faker);
        assertFalse(isValidEnZASsn(invalidIdNumber));
    }

    private static String sequentialNumber(BaseFaker f, Gender gender) {
        int number = switch (gender) {
            case FEMALE -> f.number().numberBetween(0, 5000);
            case MALE -> f.number().numberBetween(5000, 10_000);
        };
        return "%04d".formatted(number);
    }
}
