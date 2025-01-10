
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
    }

    @Test
    public void testGenerateInvalid() {
        // Mocking the dependencies
        LocalDate birthday = LocalDate.of(1990, 5, 3);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("001");
        when(faker.bool().bool()).thenReturn(true);

        // Expected base part and checksum
        String basePart = "5900503001";
        int checksum = EstonianIdNumber.checksum(basePart);

        // Expected invalid ID number
        String expectedInvalidId = basePart + (checksum + 1) % 10;

        // Test the method
        String actualInvalidId = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }

    @Test
    public void testGenerateInvalidWithDifferentBirthday() {
        // Mocking the dependencies
        LocalDate birthday = LocalDate.of(2000, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("002");
        when(faker.bool().bool()).thenReturn(false);

        // Expected base part and checksum
        String basePart = "6000101002";
        int checksum = EstonianIdNumber.checksum(basePart);

        // Expected invalid ID number
        String expectedInvalidId = basePart + (checksum + 1) % 10;

        // Test the method
        String actualInvalidId = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }
}
