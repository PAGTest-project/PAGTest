
package org.jsoup.safety;

import org.jsoup.helper.ValidationException;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeProtocolsTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
        safelist.addProtocols("a", "href", "http", "https");
        safelist.addAttributes("a", "href");
    }

    @Test
    public void testRemoveProtocols_ValidProtocols() {
        safelist.removeProtocols("a", "href", "http");
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "http://example.com")));
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "https://example.com")));
    }

    @Test
    public void testRemoveProtocols_AllProtocols() {
        safelist.removeProtocols("a", "href", "http", "https");
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "http://example.com")));
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "https://example.com")));
    }

    @Test
    public void testRemoveProtocols_InvalidTag() {
        assertThrows(ValidationException.class, () -> safelist.removeProtocols("invalidTag", "href", "http"));
    }

    @Test
    public void testRemoveProtocols_InvalidAttribute() {
        assertThrows(ValidationException.class, () -> safelist.removeProtocols("a", "invalidAttribute", "http"));
    }

    @Test
    public void testRemoveProtocols_EmptyProtocol() {
        assertThrows(ValidationException.class, () -> safelist.removeProtocols("a", "href", ""));
    }

    @Test
    public void testRemoveProtocols_NullProtocol() {
        assertThrows(ValidationException.class, () -> safelist.removeProtocols("a", "href", (String) null));
    }

    @Test
    public void testRemoveProtocols_NullProtocols() {
        assertThrows(ValidationException.class, () -> safelist.removeProtocols("a", "href", (String[]) null));
    }

    @Test
    public void testRemoveProtocols_EmptyTag() {
        assertThrows(ValidationException.class, () -> safelist.removeProtocols("", "href", "http"));
    }

    @Test
    public void testRemoveProtocols_EmptyAttribute() {
        assertThrows(ValidationException.class, () -> safelist.removeProtocols("a", "", "http"));
    }
}
