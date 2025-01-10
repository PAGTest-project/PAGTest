
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Number_digitsTest {
    private Number number;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseFaker();
        number = new Number(faker);
    }

    @Test
    public void testDigitsWithPositiveCount() {
        int count = 5;
        String result = number.digits(count);
        assertEquals(count, result.length());
        for (char c : result.toCharArray()) {
            assertTrue(Character.isDigit(c));
        }
    }

    @Test
    public void testDigitsWithZeroCount() {
        int count = 0;
        String result = number.digits(count);
        assertEquals(count, result.length());
    }

    @Test
    public void testDigitsWithNegativeCount() {
        int count = -5;
        String result = number.digits(count);
        assertEquals(0, result.length());
    }

    @Test
    public void testDigitsWithSingleDigit() {
        int count = 1;
        String result = number.digits(count);
        assertEquals(count, result.length());
        assertTrue(Character.isDigit(result.charAt(0)));
    }
}
