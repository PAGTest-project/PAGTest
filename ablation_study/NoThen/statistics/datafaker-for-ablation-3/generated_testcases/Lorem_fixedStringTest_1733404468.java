
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lorem_fixedStringTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        lorem = new Lorem(new BaseProviders());
    }

    @Test
    void testFixedStringWithPositiveNumberOfLetters() {
        int numberOfLetters = 10;
        String result = lorem.fixedString(numberOfLetters);
        assertEquals(numberOfLetters, result.length());
    }

    @Test
    void testFixedStringWithZeroNumberOfLetters() {
        int numberOfLetters = 0;
        String result = lorem.fixedString(numberOfLetters);
        assertEquals("", result);
    }

    @Test
    void testFixedStringWithNegativeNumberOfLetters() {
        int numberOfLetters = -5;
        String result = lorem.fixedString(numberOfLetters);
        assertEquals("", result);
    }
}
