
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class IdNumber_validTest {

    @Test
    public void testValidWithCountryProvider() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        IdNumberGenerator idNumberGenerator = Mockito.mock(IdNumberGenerator.class);
        IdNumber idNumber = new IdNumber(faker);
        idNumber.countryProviders.put("US", idNumberGenerator);

        when(faker.getContext().getLocale().getCountry()).thenReturn("US");
        when(idNumberGenerator.generateValid(faker)).thenReturn("123-45-6789");

        // When
        String result = idNumber.valid();

        // Then
        assertEquals("123-45-6789", result);
    }

    @Test
    public void testValidWithoutCountryProvider() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        IdNumber idNumber = new IdNumber(faker);

        when(faker.getContext().getLocale().getCountry()).thenReturn("XX");
        when(faker.numerify(Mockito.anyString())).thenReturn("123456789");
        when(faker.resolve(Mockito.anyString())).thenReturn("id_number.valid");

        // When
        String result = idNumber.valid();

        // Then
        assertEquals("123456789", result);
    }
}
