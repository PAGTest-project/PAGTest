
package net.datafaker.providers.base;

import net.datafaker.Faker;
import net.datafaker.idnumbers.IdNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class IdNumber_validTest {

    private Faker faker;
    private IdNumber idNumber;
    private IdNumberGenerator mockGenerator;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        idNumber = new IdNumber(faker);
        mockGenerator = Mockito.mock(IdNumberGenerator.class);
    }

    @Test
    public void testValidWithCountryProvider() {
        // Given
        when(mockGenerator.generateValid(faker)).thenReturn("validIdNumber");
        idNumber.countryProviders.put("US", mockGenerator);
        faker.getContext().setLocale(new Locale("en", "US"));

        // When
        String result = idNumber.valid();

        // Then
        assertEquals("validIdNumber", result);
    }

    @Test
    public void testValidWithoutCountryProvider() {
        // Given
        faker.getContext().setLocale(new Locale("en", "UnknownCountry"));

        // When
        String result = idNumber.valid();

        // Then
        assertEquals(faker.numerify(faker.resolve("id_number.valid")), result);
    }
}
