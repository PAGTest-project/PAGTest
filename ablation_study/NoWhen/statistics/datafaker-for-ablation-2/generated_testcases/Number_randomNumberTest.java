
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Number_randomNumberTest {

    @Test
    public void testRandomNumber_numberOfDigitsZero() {
        BaseFaker faker = mock(BaseFaker.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.nextInt(1)).thenReturn(0);

        Number number = new Number(faker);
        long result = number.randomNumber(0, false);

        assertEquals(0, result);
    }

    @Test
    public void testRandomNumber_strictTrue() {
        BaseFaker faker = mock(BaseFaker.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.nextLong(anyLong())).thenReturn(5L);

        Number number = new Number(faker);
        long result = number.randomNumber(3, true);

        assertTrue(result >= 100 && result < 1000);
    }

    @Test
    public void testRandomNumber_strictFalse() {
        BaseFaker faker = mock(BaseFaker.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.nextLong(anyLong())).thenReturn(500L);

        Number number = new Number(faker);
        long result = number.randomNumber(3, false);

        assertTrue(result >= 0 && result < 1000);
    }
}
