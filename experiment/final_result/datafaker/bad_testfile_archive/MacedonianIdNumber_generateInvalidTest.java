
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MacedonianIdNumber_generateInvalidTest {
    private MacedonianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MacedonianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    public void testGenerateInvalid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().numberBetween(0, 2)).thenReturn(0);
        when(faker.number().numberBetween(0, 500)).thenReturn(123);

        // When
        String result = generator.generateInvalid(faker);

        // Then
        String expectedBasePart = "010199041123";
        int expectedChecksum = (generator.checksum(expectedBasePart) + 1) % 10;
        assertEquals(expectedBasePart + expectedChecksum, result);
    }
}
