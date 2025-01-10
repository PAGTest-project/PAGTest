
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
        Attribute attribute = new Attribute(TEST_ATTRIBUTE, TEST_SCHEME + "://someValue");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, element, attribute));
    }

    @Test
    public void testRemoveProtocols_invalidProtocols() {
        assertThrows(ValidationException.class, () -> {
            safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, "invalid-scheme");
        });
    }

    @Test
    public void testRemoveProtocols_emptyProtocols() {
        assertThrows(ValidationException.class, () -> {
            safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE);
        });
    }

    @Test
    public void testRemoveProtocols_nonexistentTag() {
        assertThrows(ValidationException.class, () -> {
            safelist.removeProtocols("nonexistent-tag", TEST_ATTRIBUTE, TEST_SCHEME);
        });
    }

    @Test
    public void testRemoveProtocols_nonexistentAttribute() {
        assertThrows(ValidationException.class, () -> {
            safelist.removeProtocols(TEST_TAG, "nonexistent-attribute", TEST_SCHEME);
        });
    }
}
