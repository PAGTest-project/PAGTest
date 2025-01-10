
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Twitter_twitterIdTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        twitter = new Twitter(new BaseProviders() {});
    }

    @Test
    public void testTwitterIdValidLength() {
        int expectedLength = 10;
        String result = twitter.twitterId(expectedLength);
        assertEquals(expectedLength, result.length());
    }

    @Test
    public void testTwitterIdInvalidLength() {
        int expectedLength = 5;
        String result = twitter.twitterId(expectedLength);
        assertTrue(result.length() >= 6 && result.length() <= 25);
    }

    @Test
    public void testTwitterIdNumeric() {
        int expectedLength = 15;
        String result = twitter.twitterId(expectedLength);
        assertTrue(result.matches("\\d+"));
    }
}
