
package net.datafaker.providers.base;

import net.datafaker.Faker;
import net.datafaker.idnumbers.IdNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IdNumber_invalidTest {

    private IdNumber idNumber;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker(new Locale("en", "US"));
        idNumber = new IdNumber(faker);
    }

    @Test
    public void testInvalidWithCountryProvider() {
        IdNumberGenerator mockGenerator = mock(IdNumberGenerator.class);
        when(mockGenerator.generateInvalid(faker)).thenReturn("invalidId");
        idNumber.countryProviders.put("US", mockGenerator);

        String result = idNumber.invalid();
        assertNotNull(result);
    }

    @Test
    public void testInvalidWithoutCountryProvider() {
        when(faker.numerify(faker.resolve("id_number.invalid"))).thenReturn("invalidId");

        String result = idNumber.invalid();
        assertNotNull(result);
    }
}
