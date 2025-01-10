
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Safelist_removeProtocolsTest {
    private Safelist safelist;

    @BeforeEach
    void setUp() {
        safelist = new Safelist();
        safelist.addProtocols("a", "href", "http", "https");
    }

    @Test
    void testRemoveProtocols() {
        // Given
        String tag = "a";
        String attribute = "href";
        String[] removeProtocols = {"http"};

        // When
        safelist.removeProtocols(tag, attribute, removeProtocols);

        // Then
        Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols = safelist.protocols;
        TagName tagName = TagName.valueOf(tag);
        AttributeKey attrKey = AttributeKey.valueOf(attribute);

        assertTrue(protocols.containsKey(tagName));
        assertTrue(protocols.get(tagName).containsKey(attrKey));
        assertEquals(1, protocols.get(tagName).get(attrKey).size());
        assertTrue(protocols.get(tagName).get(attrKey).contains(Protocol.valueOf("https")));
    }

    @Test
    void testRemoveProtocols_AllProtocolsRemoved() {
        // Given
        String tag = "a";
        String attribute = "href";
        String[] removeProtocols = {"http", "https"};

        // When
        safelist.removeProtocols(tag, attribute, removeProtocols);

        // Then
        Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols = safelist.protocols;
        TagName tagName = TagName.valueOf(tag);
        AttributeKey attrKey = AttributeKey.valueOf(attribute);

        assertFalse(protocols.containsKey(tagName));
    }
}
