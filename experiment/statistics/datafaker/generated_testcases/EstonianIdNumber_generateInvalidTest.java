
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstonianIdNumber_generateInvalidTest {

    private EstonianIdNumber estonianIdNumber;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        estonianIdNumber = new EstonianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
        when(faker.timeAndDate()).thenReturn(Mockito.mock(BaseProviders.TimeAndDate.class));
    }

    @Test
    public void testGenerateInvalid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("123");

        // When
        String result = estonianIdNumber.generateInvalid(faker);

        // Then
        String expectedBasePart = "3900101123"; // Assuming firstDigit for 1990 and MALE is 3
        int expectedChecksum = (EstonianIdNumber.checksum(expectedBasePart) + 1) % 10;
        assertEquals(expectedBasePart + expectedChecksum, result);
    }
}
