
package net.datafaker.idnumbers;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RomanianIdNumber_generateInvalidTest {
    private RomanianIdNumber impl;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        impl = new RomanianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    void testGenerateInvalid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.bool().bool()).thenReturn(true);
        when(faker.number().numberBetween(1, 47)).thenReturn(1);
        when(faker.number().numberBetween(1, 1_000)).thenReturn(1);

        // When
        String result = impl.generateInvalid(faker);

        // Then
        String expectedBasePart = "1900010101001";
        int expectedChecksum = (impl.checksum(expectedBasePart) + 1) % 10;
        assertEquals(expectedBasePart + expectedChecksum, result);
    }
}
