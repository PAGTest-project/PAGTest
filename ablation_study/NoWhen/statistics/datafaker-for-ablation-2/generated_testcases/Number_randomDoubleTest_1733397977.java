
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Number_randomDoubleTest {

    @Test
    public void testRandomDouble() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        Number number = new Number(faker);
        when(faker.random().nextDouble()).thenReturn(0.5);

        // When
        double result = number.randomDouble(2, 10, 20);

        // Then
        BigDecimal expected = new BigDecimal(15).setScale(2, RoundingMode.HALF_DOWN);
        assertEquals(expected.doubleValue(), result);
    }
}
