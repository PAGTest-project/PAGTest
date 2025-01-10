
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
        Tag tag = Tag.valueOf("img", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("img", tag.getName());
        assertTrue(tag.isInline());
        assertTrue(tag.isSelfClosing());
    }

    @Test
    public void testValueOfUnknownTag() {
        Tag tag = Tag.valueOf("unknownTag", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("unknownTag", tag.getName());
        assertFalse(tag.isBlock());
    }

    @Test
    public void testValueOfKnownTagWithDifferentNamespace() {
        Tag tag = Tag.valueOf("img", "http://www.w3.org/2000/svg", settings);
        assertNotNull(tag);
        assertEquals("img", tag.getName());
        assertFalse(tag.isInline()); // SVG img is not inline
    }

    @Test
    public void testValueOfWithPreserveTagCase() {
        settings = new ParseSettings(true, false);
        Tag tag = Tag.valueOf("Img", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("Img", tag.getName());
        assertTrue(tag.isInline());
    }

    @Test
    public void testValueOfWithNormalizedTag() {
        settings = new ParseSettings(false, false);
        Tag tag = Tag.valueOf("Img", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("img", tag.getName());
        assertTrue(tag.isInline());
    }

    @Test
    public void testValueOfEmptyTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tag.valueOf("", "http://www.w3.org/1999/xhtml", settings);
        });
    }

    @Test
    public void testValueOfNullNamespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tag.valueOf("img", null, settings);
        });
    }
}
