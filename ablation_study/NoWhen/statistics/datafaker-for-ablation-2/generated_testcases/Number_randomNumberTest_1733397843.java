
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Number_randomNumberTest {

    @Test
    public void testRandomNumber_numberOfDigitsZero() {
        BaseProviders faker = mock(BaseProviders.class);
        Number number = new Number(faker);
        when(faker.random().nextInt(1)).thenReturn(0);

        long result = number.randomNumber(0, false);

        assertEquals(0, result);
    }

    @Test
    public void testRandomNumber_strictTrue() {
        BaseProviders faker = mock(BaseProviders.class);
        Number number = new Number(faker);
        when(faker.random().nextLong(anyLong())).thenReturn(5L);

        long result = number.randomNumber(3, true);

        assertTrue(result >= 100 && result < 1000);
    }

    @Test
    public void testRandomNumber_strictFalse() {
        BaseProviders faker = mock(BaseProviders.class);
        Number number = new Number(faker);
        when(faker.random().nextLong(anyLong())).thenReturn(500L);

        long result = number.randomNumber(3, false);

        assertTrue(result >= 0 && result < 1000);
    }
}
