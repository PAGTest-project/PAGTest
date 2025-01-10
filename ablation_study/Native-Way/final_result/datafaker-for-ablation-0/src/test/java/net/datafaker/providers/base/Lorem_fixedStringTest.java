
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lorem_fixedStringTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseFaker();
        lorem = new Lorem(faker);
    }

    @Test
    public void testFixedStringWithZeroLength() {
        assertEquals("", lorem.fixedString(0));
    }

    @Test
    public void testFixedStringWithPositiveLength() {
        String result = lorem.fixedString(10);
        assertEquals(10, result.length());
    }

    @Test
    public void testFixedStringWithNegativeLength() {
        assertEquals("", lorem.fixedString(-5));
    }

    @Test
    public void testFixedStringWithLengthGreaterThanSentence() {
        String result = lorem.fixedString(50);
        assertEquals(50, result.length());
    }
}
