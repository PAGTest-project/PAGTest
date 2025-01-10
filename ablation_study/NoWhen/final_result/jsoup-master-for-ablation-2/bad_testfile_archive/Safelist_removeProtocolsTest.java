
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
    public void testRemoveProtocols_ValidProtocolsRemoved() {
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute(TEST_ATTRIBUTE, TEST_SCHEME + "://someValue");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, element, attribute));
    }

    @Test
    public void testRemoveProtocols_InvalidProtocolsNotRemoved() {
        final String invalidScheme = "invalid-scheme";
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, invalidScheme);

        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute(TEST_ATTRIBUTE, TEST_SCHEME + "://someValue");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertTrue(safelist.isSafeAttribute(TEST_TAG, element, attribute));
    }

    @Test
    public void testRemoveProtocols_AllProtocolsRemoved() {
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute(TEST_ATTRIBUTE, TEST_SCHEME + "://someValue");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, element, attribute));
    }

    @Test
    public void testRemoveProtocols_TagProtocolsRemoved() {
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        assertFalse(safelist.protocols.containsKey(TagName.valueOf(TEST_TAG)));
    }

    @Test
    public void testRemoveProtocols_AttributeProtocolsRemoved() {
        safelist.removeProtocols(TEST_TAG, TEST_ATTRIBUTE, TEST_SCHEME);

        assertFalse(safelist.protocols.get(TagName.valueOf(TEST_TAG)).containsKey(AttributeKey.valueOf(TEST_ATTRIBUTE)));
    }
}
