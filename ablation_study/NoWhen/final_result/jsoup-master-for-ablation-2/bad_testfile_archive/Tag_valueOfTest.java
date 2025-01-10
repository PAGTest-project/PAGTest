
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tag_valueOfTest {

    private ParseSettings settings;

    @BeforeEach
    public void setUp() {
        settings = new ParseSettings(true, true);
    }

    @Test
    public void testValueOfKnownTag() {
        Tag tag = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("div", tag.getName());
        assertTrue(tag.isBlock());
        assertTrue(tag.formatAsBlock());
    }

    @Test
    public void testValueOfUnknownTag() {
        Tag tag = Tag.valueOf("unknownTag", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("unknownTag", tag.getName());
        assertFalse(tag.isBlock());
    }

    @Test
    public void testValueOfNormalizedTag() {
        Tag tag = Tag.valueOf("DiV", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("div", tag.normalName());
        assertTrue(tag.isBlock());
        assertTrue(tag.formatAsBlock());
    }

    @Test
    public void testValueOfPreserveTagCase() {
        settings = new ParseSettings(false, true);
        Tag tag = Tag.valueOf("DiV", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("DiV", tag.getName());
        assertTrue(tag.isBlock());
        assertTrue(tag.formatAsBlock());
    }

    @Test
    public void testValueOfEmptyTag() {
        Tag tag = Tag.valueOf("img", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("img", tag.getName());
        assertTrue(tag.isEmpty());
    }

    @Test
    public void testValueOfSelfClosingTag() {
        Tag tag = Tag.valueOf("br", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("br", tag.getName());
        assertTrue(tag.isSelfClosing());
    }

    @Test
    public void testValueOfNamespaceTag() {
        Tag tag = Tag.valueOf("svg", "http://www.w3.org/2000/svg", settings);
        assertNotNull(tag);
        assertEquals("svg", tag.getName());
        assertEquals("http://www.w3.org/2000/svg", tag.namespace());
    }

    @Test
    public void testValueOfInvalidTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tag.valueOf("", "http://www.w3.org/1999/xhtml", settings);
        });
    }

    @Test
    public void testValueOfInvalidNamespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tag.valueOf("div", null, settings);
        });
    }
}
