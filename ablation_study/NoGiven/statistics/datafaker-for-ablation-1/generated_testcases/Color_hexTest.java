
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.datafaker.service.RandomService;

public class Color_hexTest {

    @Test
    public void testHexWithHashSign() {
        BaseProviders faker = mock(BaseProviders.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.hex(6)).thenReturn("abcdef");

        Color color = new Color(faker);
        String result = color.hex(true);
        assertEquals("#abcdef", result);
    }

    @Test
    public void testHexWithoutHashSign() {
        BaseProviders faker = mock(BaseProviders.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.hex(6)).thenReturn("abcdef");

        Color color = new Color(faker);
        String result = color.hex(false);
        assertEquals("abcdef", result);
    }
}
