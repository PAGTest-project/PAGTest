
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class Text_textTest {

    @Test
    public void testText_AllFeaturesIncluded() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Number number = mock(Number.class);
        Text text = new Text(faker);
        when(faker.number()).thenReturn(number);
        when(number.numberBetween(anyInt(), anyInt())).thenReturn(10);
        when(faker.text().text(any())).thenReturn("mockedText");

        // When
        String result = text.text(5, 15, true, true, true);

        // Then
        assertNotNull(result);
    }

    @Test
    public void testText_NoFeaturesIncluded() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Number number = mock(Number.class);
        Text text = new Text(faker);
        when(faker.number()).thenReturn(number);
        when(number.numberBetween(anyInt(), anyInt())).thenReturn(10);
        when(faker.text().text(any())).thenReturn("mockedText");

        // When
        String result = text.text(5, 15, false, false, false);

        // Then
        assertNotNull(result);
    }
}
