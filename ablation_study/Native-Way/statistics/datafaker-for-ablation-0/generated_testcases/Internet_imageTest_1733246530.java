
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_imageTest {

    @Test
    public void testImage_withValidDimension() {
        Internet internet = new Internet(mock(BaseProviders.class));
        when(internet.resolve("internet.image_dimension")).thenReturn("100x200");
        assertEquals("https://picsum.photos/100/200", internet.image());
    }

    @Test
    public void testImage_withEmptyDimension() {
        Internet internet = new Internet(mock(BaseProviders.class));
        when(internet.resolve("internet.image_dimension")).thenReturn("");
        assertEquals("", internet.image());
    }
}
