
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AlbanianIdNumber_generateInvalidTest {
    private AlbanianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new AlbanianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    void testGenerateInvalid() {
        // Given
        String validPin = "1234567890";
        when(faker.number().numberBetween(93, 99)).thenReturn(95);
        when(generator.generateValid(faker)).thenReturn(validPin);

        // When
        String invalidPin = generator.generateInvalid(faker);

        // Then
        assertTrue(invalidPin.startsWith(validPin.substring(0, 2)));
        assertTrue(invalidPin.endsWith(validPin.substring(4)));
        assertTrue(invalidPin.contains("95"));
    }
}
