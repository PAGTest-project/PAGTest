
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
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
        when(randomService.nextLong(9000)).thenReturn(5000L);

        Number number = new Number(faker);
        long result = number.randomNumber(4, true);

        assertEquals(6000, result);
    }

    @Test
    public void testRandomNumber_strictFalse() {
        BaseFaker faker = mock(BaseFaker.class);
        RandomService randomService = mock(RandomService.class);
        when(faker.random()).thenReturn(randomService);
        when(randomService.nextLong(10000)).thenReturn(5000L);

        Number number = new Number(faker);
        long result = number.randomNumber(4, false);

        assertEquals(5000, result);
    }
}
