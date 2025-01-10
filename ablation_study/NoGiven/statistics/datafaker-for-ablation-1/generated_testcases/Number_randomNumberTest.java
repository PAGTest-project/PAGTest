
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import net.datafaker.service.RandomService;

public class Number_randomNumberTest {

    private Number number;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseFaker.class);
        when(faker.random()).thenReturn(Mockito.mock(RandomService.class));
        number = new Number(faker);
    }

    @Test
    public void testRandomNumber_numberOfDigitsLessThanOrEqualToZero() {
        when(faker.random().nextInt(1)).thenReturn(0);
        long result = number.randomNumber(0, true);
        assertTrue(result == 0);
    }

    @Test
    public void testRandomNumber_strictTrue() {
        when(faker.random().nextLong(9000)).thenReturn(1234L);
        long result = number.randomNumber(4, true);
        assertTrue(result >= 1000 && result < 10000);
    }

    @Test
    public void testRandomNumber_strictFalse() {
        when(faker.random().nextLong(10000)).thenReturn(5678L);
        long result = number.randomNumber(4, false);
        assertTrue(result >= 0 && result < 10000);
    }
}
