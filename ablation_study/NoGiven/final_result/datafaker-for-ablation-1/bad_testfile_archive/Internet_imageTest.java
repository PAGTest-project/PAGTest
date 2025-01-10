
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import net.datafaker.service.FakeValuesService;
import net.datafaker.service.FakerContext;

public class Internet_imageTest {

    @Test
    public void testImageWithValidDimension() {
        // Given
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext fakerContext = mock(FakerContext.class);
        Internet internet = new Internet(baseProviders);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(baseProviders.context()).thenReturn(fakerContext);
        when(fakeValuesService.resolve("internet.image_dimension", internet, fakerContext)).thenReturn("100x200");

        // When
        String result = internet.image();

        // Then
        assertEquals("https://picsum.photos/100/200", result);
    }

    @Test
    public void testImageWithEmptyDimension() {
        // Given
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext fakerContext = mock(FakerContext.class);
        Internet internet = new Internet(baseProviders);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(baseProviders.context()).thenReturn(fakerContext);
        when(fakeValuesService.resolve("internet.image_dimension", internet, fakerContext)).thenReturn("");

        // When
        String result = internet.image();

        // Then
        assertEquals("", result);
    }
}
