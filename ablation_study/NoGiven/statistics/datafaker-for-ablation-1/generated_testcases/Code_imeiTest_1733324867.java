
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class Code_imeiTest {

    private Code code;
    private BaseProviders faker;

    @BeforeEach
    public void setup() {
        faker = Mockito.mock(BaseProviders.class);
        code = new Code(faker);
    }

    @Test
    public void testImei() {
        // Given
        String prefix = "35";
        when(faker.options().option(Code.REPORTING_BODY_IDENTIFIERS)).thenReturn(prefix);
        when(faker.number().numberBetween(0, 9)).thenReturn(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // When
        String imei = code.imei();

        // Then
        assertEquals(15, imei.length());
        assertEquals(prefix.charAt(0), imei.charAt(0));
        assertEquals(prefix.charAt(1), imei.charAt(1));
        assertTrue(validateLuhnChecksum(imei));
    }

    private boolean validateLuhnChecksum(String imei) {
        int sum = 0;
        boolean alternate = false;
        for (int i = imei.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(imei.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
