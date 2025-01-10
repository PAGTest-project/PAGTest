
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_imageTest {

    @Test
    public void testImage_withValidDimension() {
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext fakerContext = mock(FakerContext.class);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(baseProviders.context()).thenReturn(fakerContext);

        Internet internet = new Internet(baseProviders);
        when(fakeValuesService.resolve("internet.image_dimension", internet, fakerContext)).thenReturn("100x200");

        String result = internet.image();

        assertEquals("https://picsum.photos/100/200", result);
    }

    @Test
    public void testImage_withEmptyDimension() {
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        FakerContext fakerContext = mock(FakerContext.class);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(baseProviders.context()).thenReturn(fakerContext);

        Internet internet = new Internet(baseProviders);
        when(fakeValuesService.resolve("internet.image_dimension", internet, fakerContext)).thenReturn("");

        String result = internet.image();

        assertEquals("", result);
    }
}
