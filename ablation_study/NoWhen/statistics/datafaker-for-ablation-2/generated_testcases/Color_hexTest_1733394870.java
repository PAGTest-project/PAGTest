
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Color_hexTest {

    @Test
    public void testHexWithHashSign() {
        BaseProviders faker = mock(BaseProviders.class);
        Color color = new Color(faker);
        when(faker.random().hex(6)).thenReturn("abcdef");

        String result = color.hex(true);
        assertEquals("#abcdef", result);
    }

    @Test
    public void testHexWithoutHashSign() {
        BaseProviders faker = mock(BaseProviders.class);
        Color color = new Color(faker);
        when(faker.random().hex(6)).thenReturn("abcdef");

        String result = color.hex(false);
        assertEquals("abcdef", result);
    }
}
