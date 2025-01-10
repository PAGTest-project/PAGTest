
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PolishIdNumber_getTest {

    @Test
    public void testGetWithGender() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        PolishIdNumber.Gender requestedGender = PolishIdNumber.Gender.FEMALE;

        PolishIdNumber polishIdNumber = new PolishIdNumber();

        // Mocking internal method calls
        when(faker.number().randomDigit()).thenReturn(1, 2, 3, 4);
        when(faker.random().nextInt(5)).thenReturn(2);

        // When
        String result = polishIdNumber.get(faker, birthDate, requestedGender);

        // Then
        assertEquals("19010112345", result);
    }

    @Test
    public void testGetWithoutGender() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        PolishIdNumber.Gender requestedGender = null;

        PolishIdNumber polishIdNumber = new PolishIdNumber();

        // Mocking internal method calls
        when(faker.number().randomDigit()).thenReturn(1, 2, 3, 4);
        when(faker.random().nextInt(5)).thenReturn(2);

        // When
        String result = polishIdNumber.get(faker, birthDate, requestedGender);

        // Then
        assertEquals("19010112345", result);
    }
}
