
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstonianIdNumber_generateInvalidTest {

    private BaseProviders faker;
    private EstonianIdNumber estonianIdNumber;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        when(faker.timeAndDate()).thenReturn(Mockito.mock(BaseProviders.TimeAndDate.class));
        estonianIdNumber = new EstonianIdNumber();
    }

    @Test
    public void testGenerateInvalid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("123");

        String expectedBasePart = "3900101123";
        int expectedChecksum = estonianIdNumber.checksum(expectedBasePart);
        String expectedInvalidId = expectedBasePart + (expectedChecksum + 1) % 10;

        String result = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, result);
    }

    @Test
    public void testGenerateInvalidWithDifferentBirthday() {
        LocalDate birthday = LocalDate.of(2000, 12, 31);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("456");

        String expectedBasePart = "5001231456";
        int expectedChecksum = estonianIdNumber.checksum(expectedBasePart);
        String expectedInvalidId = expectedBasePart + (expectedChecksum + 1) % 10;

        String result = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, result);
    }
}
