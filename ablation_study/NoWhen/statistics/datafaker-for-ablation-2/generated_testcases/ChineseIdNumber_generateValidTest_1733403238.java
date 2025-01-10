
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.service.RandomService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class ChineseIdNumber_generateValidTest {
    private ChineseIdNumber chineseIdNumber;
    private BaseProviders faker;

    @Before
    public void setUp() {
        chineseIdNumber = new ChineseIdNumber();
        faker = new BaseFaker();
    }

    @Test
    public void testGenerateValidIdNumber() {
        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber idNumber = chineseIdNumber.generateValid(faker, request);
        assertNotNull(idNumber);
        assertNotNull(idNumber.getIdNumber());
        assertNotNull(idNumber.getBirthday());
        assertNotNull(idNumber.getGender());
    }

    @Test
    public void testIdNumberChecksum() {
        char[] res = "11010519841107".toCharArray();
        res[12] = '3';
        res[13] = '3';
        res[14] = '8';
        res[15] = '0';
        String expectedIdNumber = "110105198411073380";
        assertEquals(expectedIdNumber, ChineseIdNumber.idNumber(res));
    }

    @Test
    public void testFillBirthday() {
        char[] res = new char[18];
        LocalDate birthday = LocalDate.of(1984, 11, 7);
        chineseIdNumber.fillBirthday(res, 6, birthday);
        String expectedBirthdayPart = "19841107";
        assertEquals(expectedBirthdayPart, new String(res, 6, 8));
    }

    @Test
    public void testGenerateInvalidIdNumber() {
        String invalidIdNumber = chineseIdNumber.generateInvalid(faker);
        assertNotNull(invalidIdNumber);
        assertTrue(invalidIdNumber.endsWith("42"));
    }
}
