
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
        estonianIdNumber = new EstonianIdNumber();
    }

    @Test
    public void testGenerateInvalid() {
        LocalDate birthday = LocalDate.of(1990, 5, 3);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("001");

        String expectedBasePart = "3900503001";
        int expectedChecksum = EstonianIdNumber.checksum(expectedBasePart);
        String expectedInvalidId = expectedBasePart + (expectedChecksum + 1) % 10;

        String actualInvalidId = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }

    @Test
    public void testGenerateInvalidWithDifferentBirthday() {
        LocalDate birthday = LocalDate.of(1985, 12, 25);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("002");

        String expectedBasePart = "3851225002";
        int expectedChecksum = EstonianIdNumber.checksum(expectedBasePart);
        String expectedInvalidId = expectedBasePart + (expectedChecksum + 1) % 10;

        String actualInvalidId = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }

    @Test
    public void testGenerateInvalidWithFemaleGender() {
        LocalDate birthday = LocalDate.of(2000, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().digits(3)).thenReturn("003");

        String expectedBasePart = "5000101003";
        int expectedChecksum = EstonianIdNumber.checksum(expectedBasePart);
        String expectedInvalidId = expectedBasePart + (expectedChecksum + 1) % 10;

        String actualInvalidId = estonianIdNumber.generateInvalid(faker);
        assertEquals(expectedInvalidId, actualInvalidId);
    }
}
