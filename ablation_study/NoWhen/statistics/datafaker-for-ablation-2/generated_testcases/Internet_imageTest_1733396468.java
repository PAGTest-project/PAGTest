
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_imageTest {

    @Test
    public void testImage_withValidDimension() {
        // Given
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext fakerContext = mock(FakerContext.class);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(baseProviders.context()).thenReturn(fakerContext);
        when(fakeValuesService.resolve("internet.image_dimension", any(), any())).thenReturn("100x200");

        Internet internet = new Internet(baseProviders);

        // When
        String result = internet.image();

        // Then
        assertEquals("https://picsum.photos/100/200", result);
    }

    @Test
    public void testImage_withEmptyDimension() {
        // Given
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext fakerContext = mock(FakerContext.class);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(baseProviders.context()).thenReturn(fakerContext);
        when(fakeValuesService.resolve("internet.image_dimension", any(), any())).thenReturn("");

        Internet internet = new Internet(baseProviders);

        // When
        String result = internet.image();

        // Then
        assertEquals("", result);
    }
}
