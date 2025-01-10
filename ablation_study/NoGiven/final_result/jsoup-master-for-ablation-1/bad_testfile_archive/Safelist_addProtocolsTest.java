
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
    private static final String TEST_TAG = "a";
    private static final String TEST_ATTRIBUTE = "href";
    private static final String TEST_SCHEME = "http";

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testAddProtocols_validProtocols() {
        safelist.addTags(TEST_TAG)
                .addAttributes(TEST_TAG, TEST_ATTRIBUTE)
                .addProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME, "https");

        Attributes attributes = new Attributes();
        Attribute validAttribute = new Attribute(TEST_ATTRIBUTE, TEST_SCHEME + "://someValue");
        attributes.put(validAttribute);
        Element validElement = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertTrue(safelist.isSafeAttribute(TEST_TAG, validElement, validAttribute));
    }

    @Test
    public void testAddProtocols_invalidProtocols() {
        safelist.addTags(TEST_TAG)
                .addAttributes(TEST_TAG, TEST_ATTRIBUTE)
                .addProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        Attributes attributes = new Attributes();
        Attribute invalidAttribute = new Attribute(TEST_ATTRIBUTE, "ftp://someValue");
        attributes.put(invalidAttribute);
        Element invalidElement = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, invalidElement, invalidAttribute));
    }

    @Test
    public void testAddProtocols_noProtocolsDefined() {
        safelist.addTags(TEST_TAG)
                .addAttributes(TEST_TAG, TEST_ATTRIBUTE);

        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute(TEST_ATTRIBUTE, "ftp://someValue");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertTrue(safelist.isSafeAttribute(TEST_TAG, element, attribute));
    }

    @Test
    public void testAddProtocols_emptyProtocol() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols(TEST_TAG, TEST_ATTRIBUTE, "");
        });
    }

    @Test
    public void testAddProtocols_nullProtocol() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols(TEST_TAG, TEST_ATTRIBUTE, (String) null);
        });
    }

    @Test
    public void testAddProtocols_nullProtocolsArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols(TEST_TAG, TEST_ATTRIBUTE, (String[]) null);
        });
    }

    @Test
    public void testAddProtocols_nullTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols(null, TEST_ATTRIBUTE, TEST_SCHEME);
        });
    }

    @Test
    public void testAddProtocols_emptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols("", TEST_ATTRIBUTE, TEST_SCHEME);
        });
    }

    @Test
    public void testAddProtocols_nullAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols(TEST_TAG, null, TEST_SCHEME);
        });
    }

    @Test
    public void testAddProtocols_emptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addProtocols(TEST_TAG, "", TEST_SCHEME);
        });
    }
}
