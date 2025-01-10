
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lorem_maxLengthSentenceTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        lorem = new Lorem(new BaseProviders());
    }

    @Test
    public void testMaxLengthSentenceWithPositiveLength() {
        int fixedLength = 20;
        String result = lorem.maxLengthSentence(fixedLength);
        assertEquals(fixedLength, result.length());
        assertFalse(result.endsWith(" "));
    }

    @Test
    public void testMaxLengthSentenceWithZeroLength() {
        int fixedLength = 0;
        String result = lorem.maxLengthSentence(fixedLength);
        assertEquals("", result);
    }

    @Test
    public void testMaxLengthSentenceWithNegativeLength() {
        int fixedLength = -5;
        String result = lorem.maxLengthSentence(fixedLength);
        assertEquals("", result);
    }
}
