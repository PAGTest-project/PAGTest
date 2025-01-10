
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PortugueseIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        when(faker.random().nextBoolean()).thenReturn(true);
        when(faker.options().option(PortugueseIdNumber.VALID_FIRST_DIGITS)).thenReturn('1');
        when(faker.number().digits(7)).thenReturn("1234567");

        PortugueseIdNumber idNumberGenerator = new PortugueseIdNumber();
        String result = idNumberGenerator.generateValid(faker);

        assertEquals("112345678", result);
    }
}
