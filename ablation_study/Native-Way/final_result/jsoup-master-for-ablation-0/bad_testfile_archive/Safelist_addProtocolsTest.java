
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
        Element el = new Element(Tag.valueOf("a"), "");
        Attribute attr = new Attribute("href", "http://example.com");
        assertTrue(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testAddProtocols_InvalidProtocols() {
        safelist.addProtocols("a", "href", "http", "https");
        Element el = new Element(Tag.valueOf("a"), "");
        Attribute attr = new Attribute("href", "ftp://example.com");
        assertFalse(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testAddProtocols_EmptyProtocol() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "href", "");
        });
    }

    @Test
    public void testAddProtocols_NullProtocol() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "href", (String) null);
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

    @Test
    public void testAddProtocols_NullProtocols() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "href", (String[]) null);
        });
    }
}
