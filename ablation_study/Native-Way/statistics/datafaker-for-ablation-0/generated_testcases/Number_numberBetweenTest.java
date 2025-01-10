
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import net.datafaker.service.RandomService;

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
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.nextInt(anyInt())).thenReturn(3);
        Number number = new Number(faker);
        int result = number.numberBetween(1, 10);
        assertEquals(4, result);
    }
}
