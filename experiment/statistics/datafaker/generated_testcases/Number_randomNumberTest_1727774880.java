
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Number_randomNumberTest {

    @Test
    public void testRandomNumber_numberOfDigitsZero() {
        BaseFaker faker = mock(BaseFaker.class);
        Number number = new Number(faker);
        when(faker.random().nextInt(1)).thenReturn(0);

        long result = number.randomNumber(0, false);

        assertEquals(0, result);
    }

    @Test
    public void testRandomNumber_strictTrue() {
        BaseFaker faker = mock(BaseFaker.class);
        Number number = new Number(faker);
        when(faker.random().nextLong(9000)).thenReturn(5000L);

        long result = number.randomNumber(4, true);

        assertEquals(6000L, result);
    }

    @Test
    public void testRandomNumber_strictFalse() {
        BaseFaker faker = mock(BaseFaker.class);
        Number number = new Number(faker);
        when(faker.random().nextLong(10000)).thenReturn(5000L);

        long result = number.randomNumber(4, false);

        assertEquals(5000L, result);
    }
}
