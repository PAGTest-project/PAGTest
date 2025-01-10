
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Number_numberBetweenTest {

    @Test
    public void testNumberBetween_MinEqualsMax() {
        Number number = new Number(new BaseProviders());
        int result = number.numberBetween(5, 5);
        assertEquals(5, result);
    }

    @Test
    public void testNumberBetween_MinLessThanMax() {
        Number number = new Number(new BaseProviders());
        int result = number.numberBetween(3, 8);
        assertTrue(result >= 3 && result < 8);
    }

    @Test
    public void testNumberBetween_MinGreaterThanMax() {
        Number number = new Number(new BaseProviders());
        int result = number.numberBetween(8, 3);
        assertTrue(result >= 3 && result < 8);
    }
}
