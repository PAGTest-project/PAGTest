
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PortugueseIdNumber_generateInvalidTest {

    @Test
    public void testGenerateInvalid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        when(faker.number().digits(8)).thenReturn("12345678");
        PortugueseIdNumber portugueseIdNumber = new PortugueseIdNumber();

        // When
        String result = portugueseIdNumber.generateInvalid(faker);

        // Then
        assertEquals("1234567829", result);
    }
}
