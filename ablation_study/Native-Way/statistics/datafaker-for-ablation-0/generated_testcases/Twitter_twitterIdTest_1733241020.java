
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Twitter_twitterIdTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        twitter = new Twitter(new BaseProviders());
    }

    @Test
    public void testTwitterIdValidLength() {
        int expectedLength = 10;
        String id = twitter.twitterId(expectedLength);
        assertEquals(expectedLength, id.length());
    }

    @Test
    public void testTwitterIdInvalidLength() {
        int expectedLength = 5;
        String id = twitter.twitterId(expectedLength);
        assertTrue(id.length() >= 6 && id.length() <= 25);
    }

    @Test
    public void testTwitterIdNumeric() {
        int expectedLength = 15;
        String id = twitter.twitterId(expectedLength);
        assertTrue(id.matches("\\d+"));
    }
}
