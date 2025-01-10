
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AmericanIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        // Given
        BaseProviders f = Mockito.mock(BaseProviders.class);
        AmericanIdNumber americanIdNumber = new AmericanIdNumber();

        // When
        when(f.regexify("[0-8]\\d{2}-\\d{2}-\\d{4}")).thenReturn("123-45-6789");

        // Then
        String result = americanIdNumber.generateValid(f);
        assertTrue(Pattern.compile("[0-8]\\d{2}-\\d{2}-\\d{4}").matcher(result).matches());
    }
}
