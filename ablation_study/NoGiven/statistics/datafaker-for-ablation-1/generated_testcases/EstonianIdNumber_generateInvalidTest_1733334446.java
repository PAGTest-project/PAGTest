
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
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
        estonianIdNumber = new EstonianIdNumber();
    }

    @Test
    public void testGenerateInvalid() {
        // Mocking the dependencies
        LocalDate birthday = LocalDate.of(1990, 5, 3);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("029");

        // Expected basePart and checksum
        String basePart = "3900503029";
        int checksum = EstonianIdNumber.checksum(basePart);

        // Expected invalid ID number
        String expectedInvalidId = basePart + ((checksum + 1) % 10);

        // Test the method
        String actualInvalidId = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }

    @Test
    public void testGenerateInvalidWithDifferentBirthday() {
        // Mocking the dependencies
        LocalDate birthday = LocalDate.of(1985, 1, 23);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("421");

        // Expected basePart and checksum
        String basePart = "3850123421";
        int checksum = EstonianIdNumber.checksum(basePart);

        // Expected invalid ID number
        String expectedInvalidId = basePart + ((checksum + 1) % 10);

        // Test the method
        String actualInvalidId = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }
}
