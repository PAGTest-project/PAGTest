
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Twitter_twitterIdTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        twitter = new Twitter(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }
        });
    }

    @Test
    void testTwitterIdValidLength() {
        int expectedLength = 10;
        String twitterId = twitter.twitterId(expectedLength);
        assertEquals(expectedLength, twitterId.length());
    }

    @Test
    void testTwitterIdInvalidLength() {
        int expectedLength = 5;
        String twitterId = twitter.twitterId(expectedLength);
        assertTrue(twitterId.length() >= 6 && twitterId.length() <= 25);
    }

    @Test
    void testTwitterIdNumeric() {
        int expectedLength = 12;
        String twitterId = twitter.twitterId(expectedLength);
        assertTrue(twitterId.matches("\\d+"));
    }
}
