
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Number_digitsTest {

    @Test
    public void testDigits() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Number number = new Number(faker);
        byte[] mockBytes = {1, -2, 3, -4, 5};
        when(faker.random().nextRandomBytes(5)).thenReturn(mockBytes);

        // When
        String result = number.digits(5);

        // Then
        assertEquals("18365", result);
    }
}
