
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChineseIdNumber_generateValidTest {

    private BaseProviders faker;
    private ChineseIdNumber chineseIdNumber;
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        randomService = Mockito.mock(RandomService.class);
        chineseIdNumber = new ChineseIdNumber();
    }

    @Test
    public void testGenerateValid() {
        // Mock the dependencies
        when(faker.random()).thenReturn(randomService);
        when(faker.options().option(ChineseIdNumber.LOCATIONS)).thenReturn("110000");
        when(randomService.nextInt(10)).thenReturn(1, 2, 3);
        when(faker.date().birthday()).thenReturn(LocalDate.of(1990, 1, 1));

        // Create a mock IdNumberRequest
        IdNumberRequest request = Mockito.mock(IdNumberRequest.class);

        // Call the method under test
        PersonIdNumber result = chineseIdNumber.generateValid(faker, request);

        // Verify the result
        assertEquals("11000019900101123", result.getIdNumber());
    }

    @Test
    public void testIdNumberChecksum() {
        char[] res = "11000019900101123".toCharArray();
        String expected = "110000199001011234";
        String actual = ChineseIdNumber.idNumber(res);
        assertEquals(expected, actual);
    }

    @Test
    public void testFillBirthday() {
        char[] res = new char[18];
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        chineseIdNumber.fillBirthday(res, 6, birthday);
        String expected = "19900101";
        String actual = new String(res, 6, 8);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateInvalid() {
        // Mock the dependencies
        when(faker.random()).thenReturn(randomService);
        when(faker.options().option(ChineseIdNumber.LOCATIONS)).thenReturn("110000");
        when(randomService.nextInt(10)).thenReturn(1, 2, 3);
        when(faker.date().birthday()).thenReturn(LocalDate.of(1990, 1, 1));

        // Call the method under test
        String result = chineseIdNumber.generateInvalid(faker);

        // Verify the result
        assertEquals("1100001990010112342", result);
    }
}
