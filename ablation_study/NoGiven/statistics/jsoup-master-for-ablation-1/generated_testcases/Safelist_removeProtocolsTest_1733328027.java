
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
    private static final String TEST_TAG = "a";
    private static final String TEST_ATTRIBUTE = "href";
    private static final String TEST_SCHEME = "http";
    private static final String INVALID_SCHEME = "invalid-scheme";

    @BeforeEach
    public void setUp() {
        safelist = Safelist.none()
                .addAttributes(TEST_TAG, TEST_ATTRIBUTE)
                .addProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);
    }

    @Test
    public void testRemoveProtocols_validProtocols() {
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        Attributes attributes = new Attributes();
        Attribute invalidAttribute = new Attribute(TEST_ATTRIBUTE, TEST_SCHEME + "://someValue");
        attributes.put(invalidAttribute);
        Element invalidElement = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, invalidElement, invalidAttribute));
    }

    @Test
    public void testRemoveProtocols_invalidProtocols() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, INVALID_SCHEME);
        });
    }

    @Test
    public void testRemoveProtocols_allProtocols() {
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        Attributes attributes = new Attributes();
        Attribute validAttribute = new Attribute(TEST_ATTRIBUTE, "mailto:someValue");
        attributes.put(validAttribute);
        Element validElement = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, validElement, validAttribute));
    }

    @Test
    public void testRemoveProtocols_emptyProtocols() {
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        Attributes attributes = new Attributes();
        Attribute emptyAttribute = new Attribute(TEST_ATTRIBUTE, "");
        attributes.put(emptyAttribute);
        Element emptyElement = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, emptyElement, emptyAttribute));
    }
}
