
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

class Safelist_removeTagsTest {

    private Safelist safelist;

    @BeforeEach
    void setUp() {
        safelist = new Safelist();
        safelist.addTags("a", "b", "blockquote");
        safelist.addAttributes("a", "href");
        safelist.addAttributes("blockquote", "cite");
        safelist.addProtocols("a", "href", "http", "https");
        safelist.addProtocols("blockquote", "cite", "http", "https");
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
    }

    @Test
    void testRemoveTags() {
        // Given
        String[] tagsToRemove = {"a", "b"};

        // When
        safelist.removeTags(tagsToRemove);

        // Then
        assertFalse(safelist.isSafeTag("a"));
        assertFalse(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("blockquote"));

        assertNull(safelist.attributes.get(TagName.valueOf("a")));
        assertNull(safelist.attributes.get(TagName.valueOf("b")));
        assertNotNull(safelist.attributes.get(TagName.valueOf("blockquote")));

        assertNull(safelist.enforcedAttributes.get(TagName.valueOf("a")));
        assertNull(safelist.enforcedAttributes.get(TagName.valueOf("b")));
        assertNotNull(safelist.enforcedAttributes.get(TagName.valueOf("blockquote")));

        assertNull(safelist.protocols.get(TagName.valueOf("a")));
        assertNull(safelist.protocols.get(TagName.valueOf("b")));
        assertNotNull(safelist.protocols.get(TagName.valueOf("blockquote")));
    }
}
