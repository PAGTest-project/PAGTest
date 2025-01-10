
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import net.datafaker.service.RandomService;

public class Number_digitsTest {

    @Test
    public void testDigits() {
        // Given
        BaseFaker faker = mock(BaseFaker.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        byte[] mockBytes = {1, -2, 3, -4, 5};
        when(randomService.nextRandomBytes(5)).thenReturn(mockBytes);
        Number number = new Number(faker);

        // When
        String result = number.digits(5);

        // Then
        assertEquals("18365", result);
    }
}
