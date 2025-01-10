
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
                // Implementation not needed for testing
            }
        });
    }

    @Test
    public void testGetLinkWithValidExtraLength() {
        String username = twitter.userName();
        int extraLength = 5;
        String link = twitter.getLink(username, extraLength);
        assertTrue(link.startsWith("https://twitter.com/" + username + "/"));
        assertEquals(link.length(), "https://twitter.com/".length() + username.length() + extraLength + 1);
    }

    @Test
    public void testGetLinkWithExtraLengthLessThanFour() {
        String username = twitter.userName();
        int extraLength = 3;
        String link = twitter.getLink(username, extraLength);
        assertTrue(link.startsWith("https://twitter.com/" + username + "/"));
        assertEquals(link.length(), "https://twitter.com/".length() + username.length() + extraLength + 1);
    }
}
