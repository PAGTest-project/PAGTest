
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
        faker = new BaseProviders();
        number = new Number(faker);
    }

    @Test
    public void testDigits() {
        int count = 5;
        String result = number.digits(count);
        assertEquals(count, result.length());
        for (char c : result.toCharArray()) {
            assertTrue(Character.isDigit(c));
        }
    }
}
