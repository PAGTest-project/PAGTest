
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
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
    public void testAddProtocols_validProtocols() {
        safelist.addProtocols("a", "href", "http", "https");

        Attributes attributes = new Attributes();
        Attribute validAttribute = new Attribute("href", "http://example.com");
        attributes.put(validAttribute);
        Element validElement = new Element(Tag.valueOf("a"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", validElement, validAttribute));
    }

    @Test
    public void testAddProtocols_invalidProtocols() {
        safelist.addProtocols("a", "href", "http", "https");

        Attributes attributes = new Attributes();
        Attribute invalidAttribute = new Attribute("href", "ftp://example.com");
        attributes.put(invalidAttribute);
        Element invalidElement = new Element(Tag.valueOf("a"), "", attributes);

        assertFalse(safelist.isSafeAttribute("a", invalidElement, invalidAttribute));
    }

    @Test
    public void testAddProtocols_emptyProtocol() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "href", "");
        });
    }

    @Test
    public void testAddProtocols_nullProtocol() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "href", (String) null);
        });
    }

    @Test
    public void testAddProtocols_nullProtocolsArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "href", (String[]) null);
        });
    }

    @Test
    public void testAddProtocols_emptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("", "href", "http");
        });
    }

    @Test
    public void testAddProtocols_emptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("a", "", "http");
        });
    }
}
