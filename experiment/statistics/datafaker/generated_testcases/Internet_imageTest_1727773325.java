
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_imageTest {

    @Test
    public void testImage_withValidDimension() {
        Internet internet = mock(Internet.class);
        String dimension = "100x200";
        when(internet.resolve("internet.image_dimension")).thenReturn(dimension);
        when(internet.image(100, 200)).thenReturn("https://picsum.photos/100/200");

        String result = internet.image();

        assertEquals("https://picsum.photos/100/200", result);
    }

    @Test
    public void testImage_withEmptyDimension() {
        Internet internet = mock(Internet.class);
        String dimension = "";
        when(internet.resolve("internet.image_dimension")).thenReturn(dimension);

        String result = internet.image();

        assertEquals("", result);
    }
}
