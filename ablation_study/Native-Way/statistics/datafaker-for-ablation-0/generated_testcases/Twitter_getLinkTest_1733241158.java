
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Twitter_getLinkTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        twitter = new Twitter(new BaseProviders());
    }

    @Test
    public void testGetLinkWithValidExtraLength() {
        String username = "testUser";
        int extraLength = 5;
        String link = twitter.getLink(username, extraLength);
        assertEquals("https://twitter.com/" + username + "/", link.substring(0, 25));
        assertTrue(link.length() == 31); // 25 (base) + 5 (extra) + 1 (/)
    }

    @Test
    public void testGetLinkWithExtraLengthLessThanFour() {
        String username = "testUser";
        int extraLength = 3;
        String link = twitter.getLink(username, extraLength);
        assertEquals("https://twitter.com/" + username + "/", link.substring(0, 25));
        assertTrue(link.length() == 29); // 25 (base) + 3 (extra) + 1 (/)
    }
}
