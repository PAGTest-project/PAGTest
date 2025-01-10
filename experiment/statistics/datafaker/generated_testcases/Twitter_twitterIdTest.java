
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Twitter_twitterIdTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        twitter = new Twitter(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
    }

    @Test
    public void testTwitterIdValidLength() {
        int expectedLength = 10;
        String twitterId = twitter.twitterId(expectedLength);
        assertEquals(expectedLength, twitterId.length());
    }

    @Test
    public void testTwitterIdInvalidLength() {
        int expectedLength = 5;
        String twitterId = twitter.twitterId(expectedLength);
        assertTrue(twitterId.length() >= 6 && twitterId.length() <= 25);
    }
}
