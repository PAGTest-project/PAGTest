
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class MacedonianIdNumber_generateInvalidTest {
    private MacedonianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MacedonianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    void testGenerateInvalid() {
        // Mocking dependencies
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().numberBetween(0, 2)).thenReturn(0); // Mocking gender selection

        // Expected basePart and checksum
        String basePart = "010199041000"; // Example basePart
        int checksumValue = generator.checksum(basePart);
        String expectedInvalidId = basePart + ((checksumValue + 1) % 10);

        // Test
        String result = generator.generateInvalid(faker);
        assertEquals(expectedInvalidId, result);
    }

    @Test
    void testChecksum() {
        assertEquals(6, generator.checksum("010100650000"));
        assertEquals(4, generator.checksum("923456789012"));
    }
}
