
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_imageTest {

    @Test
    public void testImageWithValidDimension() {
        // Given
        Internet internet = new Internet(mock(BaseProviders.class));
        when(internet.resolve("internet.image_dimension")).thenReturn("100x200");

        // When
        String result = internet.image();

        // Then
        assertEquals("https://picsum.photos/100/200", result);
    }

    @Test
    public void testImageWithEmptyDimension() {
        // Given
        Internet internet = new Internet(mock(BaseProviders.class));
        when(internet.resolve("internet.image_dimension")).thenReturn("");

        // When
        String result = internet.image();

        // Then
        assertEquals("", result);
    }
}
