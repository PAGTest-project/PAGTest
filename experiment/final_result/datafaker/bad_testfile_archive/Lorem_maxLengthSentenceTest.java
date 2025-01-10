
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lorem_maxLengthSentenceTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }

            @Override
            public RandomService random() {
                return new RandomService();
            }
        };
        lorem = new Lorem(faker);
    }

    @Test
    public void testMaxLengthSentenceWithPositiveLength() {
        int fixedLength = 10;
        String result = lorem.maxLengthSentence(fixedLength);
        assertEquals(fixedLength, result.length());
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
