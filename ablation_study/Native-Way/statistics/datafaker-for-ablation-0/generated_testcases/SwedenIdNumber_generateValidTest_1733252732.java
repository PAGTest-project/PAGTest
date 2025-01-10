
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SwedenIdNumber_generateValidTest {

    private SwedenIdNumber swedenIdNumber;
    private BaseProviders f;
    private IdNumber.IdNumberRequest request;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        f = Mockito.mock(BaseProviders.class);
        request = Mockito.mock(IdNumber.IdNumberRequest.class);
    }

    @Test
    public void testGenerateValid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(f.numerify("###")).thenReturn("123");
        when(f.options().option(SwedenIdNumber.PLUS_MINUS)).thenReturn("-");
        when(Utils.gender(f, request)).thenReturn(net.datafaker.providers.base.Gender.MALE);

        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        String expectedBasePart = "900101-123";
        String expectedIdNumber = expectedBasePart + SwedenIdNumber.calculateChecksum(expectedBasePart);
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(net.datafaker.providers.base.Gender.MALE, result.getGender());
    }

    @Test
    public void testGenerateInvalid() {
        String invalidSsn = swedenIdNumber.generateInvalid(f);
        assertEquals(false, SwedenIdNumber.isValidSwedishSsn(invalidSsn));
    }
}
