
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
        PersonIdNumber personIdNumber = generator.generateValid(faker, request);

        assertNotNull(personIdNumber);
        assertNotNull(personIdNumber.getIdNumber());
        assertNotNull(personIdNumber.getBirthDate());
        assertNotNull(personIdNumber.getGender());

        String idNumber = personIdNumber.getIdNumber();
        LocalDate birthDate = personIdNumber.getBirthDate();
        Gender gender = personIdNumber.getGender();

        String expectedBasePart = generator.yy(birthDate.getYear()) +
                generator.mm(birthDate.getMonthValue(), gender) +
                generator.dd(birthDate.getDayOfMonth()) +
                generator.sss(faker);

        String expectedIdNumber = expectedBasePart + generator.checksum(expectedBasePart);

        assertEquals(expectedIdNumber, idNumber);
    }

    @Test
    public void testGenerateInvalid() {
        String invalidIdNumber = generator.generateInvalid(faker);
        assertNotNull(invalidIdNumber);
    }

    @Test
    public void testYy() {
        assertEquals("00", generator.yy(1800));
        assertEquals("01", generator.yy(1801));
        assertEquals("09", generator.yy(1809));
        assertEquals("10", generator.yy(1810));
        assertEquals("99", generator.yy(1899));
    }

    @Test
    public void testMm() {
        assertEquals("01", generator.mm(1, Gender.MALE));
        assertEquals("02", generator.mm(2, Gender.MALE));
        assertEquals("09", generator.mm(9, Gender.MALE));
        assertEquals("12", generator.mm(12, Gender.MALE));
        assertEquals("51", generator.mm(1, Gender.FEMALE));
        assertEquals("52", generator.mm(2, Gender.FEMALE));
        assertEquals("58", generator.mm(8, Gender.FEMALE));
        assertEquals("62", generator.mm(12, Gender.FEMALE));
    }

    @Test
    public void testDd() {
        assertEquals("01", generator.dd(1));
        assertEquals("02", generator.dd(2));
        assertEquals("09", generator.dd(9));
        assertEquals("10", generator.dd(10));
        assertEquals("31", generator.dd(31));
    }

    @Test
    public void testSss() {
        String sss = generator.sss(faker);
        assertNotNull(sss);
        assertEquals(3, sss.length());
    }

    @Test
    public void testChecksum() {
        assertEquals('W', generator.checksum("000000000"));
        assertEquals('A', generator.checksum("000000001"));
        assertEquals('B', generator.checksum("000000002"));
        assertEquals('C', generator.checksum("000000003"));
        assertEquals('D', generator.checksum("000000004"));
    }
}
