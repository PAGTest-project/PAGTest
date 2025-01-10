
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_imageTest {

    @Test
    public void testImage_withValidDimension() {
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(fakeValuesService.resolve("internet.image_dimension", any(), any(), any())).thenReturn("100x200");

        Internet internet = new Internet(baseProviders);
        assertEquals("https://picsum.photos/100/200", internet.image());
    }

    @Test
    public void testImage_withEmptyDimension() {
        BaseProviders baseProviders = mock(BaseProviders.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        when(baseProviders.fakeValuesService()).thenReturn(fakeValuesService);
        when(fakeValuesService.resolve("internet.image_dimension", any(), any(), any())).thenReturn("");

        Internet internet = new Internet(baseProviders);
        assertEquals("", internet.image());
    }
}
