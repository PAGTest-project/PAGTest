
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.service.RandomService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ChineseIdNumber_generateValidTest {

    private BaseProviders faker;
    private IdNumberRequest request;
    private RandomService randomService;
    private ChineseIdNumber chineseIdNumber;

    @Before
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        request = Mockito.mock(IdNumberRequest.class);
        randomService = Mockito.mock(RandomService.class);
        chineseIdNumber = new ChineseIdNumber();

        when(faker.random()).thenReturn(randomService);
        when(faker.options().option(ChineseIdNumber.LOCATIONS)).thenReturn("110000");
        when(randomService.nextInt(10)).thenReturn(1, 2, 3);
    }

    @Test
    public void testGenerateValid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(chineseIdNumber.birthday(faker, request)).thenReturn(birthday);
        when(chineseIdNumber.gender(faker, request)).thenReturn("Male");

        PersonIdNumber result = chineseIdNumber.generateValid(faker, request);

        assertEquals("11000019900101123", result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals("Male", result.getGender());
    }

    @Test
    public void testIdNumberChecksum() {
        char[] res = "11000019900101123".toCharArray();
        String expectedIdNumber = "110000199001011231";

        String result = ChineseIdNumber.idNumber(res);

        assertEquals(expectedIdNumber, result);
    }

    @Test
    public void testFillBirthday() {
        char[] res = new char[18];
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        int offset = 6;

        chineseIdNumber.fillBirthday(res, offset, birthday);

        assertEquals('1', res[offset]);
        assertEquals('9', res[offset + 1]);
        assertEquals('9', res[offset + 2]);
        assertEquals('0', res[offset + 3]);
        assertEquals('0', res[offset + 4]);
        assertEquals('1', res[offset + 5]);
        assertEquals('0', res[offset + 6]);
        assertEquals('1', res[offset + 7]);
    }
}
