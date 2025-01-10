
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lorem_maxLengthSentenceTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseFaker();
        lorem = new Lorem(faker);
    }

    @Test
    public void testMaxLengthSentenceWithZeroLength() {
        assertEquals("", lorem.maxLengthSentence(0));
    }

    @Test
    public void testMaxLengthSentenceWithPositiveLength() {
        int fixedLength = 10;
        String result = lorem.maxLengthSentence(fixedLength);
        assertEquals(fixedLength, result.length());
    }

    @Test
    public void testMaxLengthSentenceWithSpaceAtEnd() {
        int fixedLength = 15;
        String result = lorem.maxLengthSentence(fixedLength);
        assertEquals(fixedLength, result.length());
        assertEquals(false, result.endsWith(" "));
    }
}
