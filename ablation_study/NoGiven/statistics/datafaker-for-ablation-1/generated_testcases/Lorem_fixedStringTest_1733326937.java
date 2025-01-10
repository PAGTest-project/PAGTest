
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lorem_fixedStringTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders() {
            @Override
            public Lorem lorem() {
                return new Lorem(this);
            }
        };
        lorem = baseProviders.lorem();
    }

    @Test
    public void testFixedStringWithZeroLength() {
        String result = lorem.fixedString(0);
        assertEquals("", result);
    }

    @Test
    public void testFixedStringWithPositiveLength() {
        int numberOfLetters = 10;
        String result = lorem.fixedString(numberOfLetters);
        assertEquals(numberOfLetters, result.length());
    }

    @Test
    public void testFixedStringWithNegativeLength() {
        String result = lorem.fixedString(-5);
        assertEquals("", result);
    }
}
