
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeTagsTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testRemoveTags_SingleTag() {
        safelist.addTags("a", "b", "c");
        safelist.removeTags("b");

        assertFalse(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("c"));
    }

    @Test
    public void testRemoveTags_MultipleTags() {
        safelist.addTags("a", "b", "c", "d");
        safelist.removeTags("b", "d");

        assertFalse(safelist.isSafeTag("b"));
        assertFalse(safelist.isSafeTag("d"));
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("c"));
    }

    @Test
    public void testRemoveTags_NonExistentTag() {
        safelist.addTags("a", "b");
        safelist.removeTags("c");

        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertFalse(safelist.isSafeTag("c"));
    }

    @Test
    public void testRemoveTags_AttributesRemoved() {
        safelist.addTags("a", "b");
        safelist.addAttributes("a", "href", "class");
        safelist.addAttributes("b", "src", "alt");
        safelist.removeTags("a");

        assertFalse(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertNull(safelist.attributes.get(TagName.valueOf("a")));
        assertNotNull(safelist.attributes.get(TagName.valueOf("b")));
    }

    @Test
    public void testRemoveTags_EnforcedAttributesRemoved() {
        safelist.addTags("a", "b");
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("b", "width", "100");
        safelist.removeTags("a");

        assertFalse(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertNull(safelist.enforcedAttributes.get(TagName.valueOf("a")));
        assertNotNull(safelist.enforcedAttributes.get(TagName.valueOf("b")));
    }

    @Test
    public void testRemoveTags_ProtocolsRemoved() {
        safelist.addTags("a", "b");
        safelist.addProtocols("a", "href", "http", "https");
        safelist.addProtocols("b", "src", "ftp");
        safelist.removeTags("a");

        assertFalse(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertNull(safelist.protocols.get(TagName.valueOf("a")));
        assertNotNull(safelist.protocols.get(TagName.valueOf("b")));
    }

    @Test
    public void testRemoveTags_NullInput() {
        assertThrows(IllegalArgumentException.class, () -> safelist.removeTags(null));
    }

    @Test
    public void testRemoveTags_EmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> safelist.removeTags());
    }

    @Test
    public void testRemoveTags_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> safelist.removeTags(""));
    }

    @Test
    public void testRemoveTags_InvalidTag() {
        assertThrows(IllegalArgumentException.class, () -> safelist.removeTags("invalidTag"));
    }
}
