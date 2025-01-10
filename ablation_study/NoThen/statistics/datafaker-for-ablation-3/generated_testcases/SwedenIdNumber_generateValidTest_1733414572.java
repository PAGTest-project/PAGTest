
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class SwedenIdNumber_generateValidTest {

    private SwedenIdNumber swedenIdNumber;
    private BaseProviders f;
    private IdNumber.IdNumberRequest request;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        f = mock(BaseProviders.class);
        request = mock(IdNumber.IdNumberRequest.class);
    }

    @Test
    public void testGenerateValid_ValidBirthdayAndGender() {
        LocalDate birthday = LocalDate.of(1990, 10, 10);
        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(f.numerify("###")).thenReturn("123");
        when(f.options().option(anyString())).thenReturn("-");
        when(Utils.gender(f, request)).thenReturn("Male");

        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        String expectedBasePart = "901010-123";
        String expectedIdNumber = expectedBasePart + swedenIdNumber.calculateChecksum(expectedBasePart);
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals("Male", result.getGender());
    }

    @Test
    public void testGenerateValid_InvalidBirthday() {
        when(Utils.birthday(f, request)).thenReturn(null);

        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        assertEquals(null, result);
    }

    @Test
    public void testGenerateValid_InvalidGender() {
        LocalDate birthday = LocalDate.of(1990, 10, 10);
        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(f.numerify("###")).thenReturn("123");
        when(f.options().option(anyString())).thenReturn("-");
        when(Utils.gender(f, request)).thenReturn(null);

        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        String expectedBasePart = "901010-123";
        String expectedIdNumber = expectedBasePart + swedenIdNumber.calculateChecksum(expectedBasePart);
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(null, result.getGender());
    }
}
