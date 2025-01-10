
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Twitter_getLinkTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        twitter = new Twitter(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation
            }

            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public int nextInt(int bound) {
                        return 0; // Dummy implementation
                    }

                    @Override
                    public int nextInt(int min, int max) {
                        return 0; // Dummy implementation
                    }

                    @Override
                    public double nextDouble() {
                        return 0.0; // Dummy implementation
                    }
                };
            }
        });
    }

    @Test
    public void testGetLinkWithValidExtraLength() {
        String username = "testUser";
        int extraLength = 5;
        String link = twitter.getLink(username, extraLength);
        String expectedPrefix = "https://twitter.com/" + username + "/";
        assertTrue(link.startsWith(expectedPrefix));
        assertEquals(expectedPrefix.length() + extraLength, link.length());
    }

    @Test
    public void testGetLinkWithExtraLengthLessThanFour() {
        String username = "testUser";
        int extraLength = 3;
        String link = twitter.getLink(username, extraLength);
        String expectedPrefix = "https://twitter.com/" + username + "/";
        assertTrue(link.startsWith(expectedPrefix));
        assertEquals(expectedPrefix.length() + extraLength, link.length());
    }
}
