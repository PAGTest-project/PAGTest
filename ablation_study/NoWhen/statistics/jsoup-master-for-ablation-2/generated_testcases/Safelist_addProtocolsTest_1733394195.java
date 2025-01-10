
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_addProtocolsTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testAddProtocols_ValidProtocols() {
        safelist.addProtocols("a", "href", "http", "https");
        Element element = new Element(Tag.valueOf("a"), "");
        element.attr("href", "http://example.com");
        Attribute attr = element.attributes().get("href");
        assertTrue(safelist.isSafeAttribute("a", element, attr));
    }

    @Test
    public void testAddProtocols_InvalidProtocols() {
        safelist.addProtocols("a", "href", "http", "https");
        Element element = new Element(Tag.valueOf("a"), "");
        element.attr("href", "ftp://example.com");
        Attribute attr = element.attributes().get("href");
        assertFalse(safelist.isSafeAttribute("a", element, attr));
    }

    @Test
    public void testAddProtocols_EmptyProtocol() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "href", "");
        });
    }

    @Test
    public void testAddProtocols_NullProtocols() {
        assertThrows(NullPointerException.class, () -> {
            safelist.addProtocols("a", "href", (String[]) null);
        });
    }

    @Test
    public void testAddProtocols_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("", "href", "http");
        });
    }

    @Test
    public void testAddProtocols_EmptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "", "http");
        });
    }
}
