
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Number_numberBetweenTest {

    @Test
    public void testNumberBetweenWithEqualMinMax() {
        BaseProviders faker = mock(BaseProviders.class);
        Number number = new Number(faker);
        int result = number.numberBetween(5, 5);
        assertEquals(5, result);
    }

    @Test
    public void testNumberBetweenWithDifferentMinMax() {
        BaseProviders faker = mock(BaseProviders.class);
        when(faker.random().nextInt(anyInt())).thenReturn(3);
        Number number = new Number(faker);
        int result = number.numberBetween(1, 10);
        assertEquals(4, result);
    }
}
