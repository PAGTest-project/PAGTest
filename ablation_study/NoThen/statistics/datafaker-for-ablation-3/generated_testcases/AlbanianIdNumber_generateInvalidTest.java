
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import net.datafaker.providers.base.Gender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AlbanianIdNumber_generateInvalidTest {

    @Test
    public void testGenerateInvalid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        AlbanianIdNumber idNumberGenerator = new AlbanianIdNumber();

        // Mock generateValid to return a known valid PIN
        when(faker.number().numberBetween(93, 99)).thenReturn(95);
        when(faker.number().digits(3)).thenReturn("123");
        when(faker.date().birthday()).thenReturn(LocalDate.of(1990, 5, 15));
        when(faker.gender()).thenReturn(Gender.FEMALE);

        String validPin = "905515123W"; // Example valid PIN
        when(idNumberGenerator.generateValid(faker)).thenReturn(validPin);

        // When
        String invalidPin = idNumberGenerator.generateInvalid(faker);

        // Then
        assertEquals("909515123W", invalidPin);
    }
}
