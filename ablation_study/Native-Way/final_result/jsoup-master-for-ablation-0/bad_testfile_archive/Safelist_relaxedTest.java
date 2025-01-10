
package org.jsoup.safety;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.jsoup.nodes.Attribute;

public class Safelist_relaxedTest {

    @Test
    public void testRelaxedSafelist() {
        Safelist relaxedSafelist = Safelist.relaxed();

        // Test tags
        assertTrue(relaxedSafelist.isSafeTag("a"));
        assertTrue(relaxedSafelist.isSafeTag("img"));
        assertTrue(relaxedSafelist.isSafeTag("table"));
        assertFalse(relaxedSafelist.isSafeTag("script"));

        // Test attributes
        assertTrue(relaxedSafelist.isSafeAttribute("a", new Attribute("href", "http://example.com")));
        assertTrue(relaxedSafelist.isSafeAttribute("img", new Attribute("src", "http://example.com/image.jpg")));
        assertFalse(relaxedSafelist.isSafeAttribute("img", new Attribute("onclick", "alert('xss')")));

        // Test protocols
        assertTrue(relaxedSafelist.isSafeAttribute("a", new Attribute("href", "http://example.com")));
        assertTrue(relaxedSafelist.isSafeAttribute("a", new Attribute("href", "https://example.com")));
        assertFalse(relaxedSafelist.isSafeAttribute("a", new Attribute("href", "javascript:alert('xss')")));
    }
}
