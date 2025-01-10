
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lorem_maxLengthSentenceTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders() {
            @Override
            public Lorem lorem() {
                return new Lorem(this);
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }
        };
        lorem = baseProviders.lorem();
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

    @Test
    public void testMaxLengthSentenceWithSpaceAtEnd() {
        int fixedLength = 15;
        String result = lorem.maxLengthSentence(fixedLength);
        assertEquals(fixedLength, result.length());
        assertFalse(result.endsWith(" "));
    }
}
