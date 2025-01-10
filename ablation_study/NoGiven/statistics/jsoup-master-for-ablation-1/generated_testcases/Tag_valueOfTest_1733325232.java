
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
        assertTrue(Tag.isKnownTag(tag.getName()));
        assertEquals("div", tag.getName());
        assertEquals("div", tag.normalName());
    }

    @Test
    public void testValueOfUnknownTag() {
        Tag tag = Tag.valueOf("unknownTag", "http://www.w3.org/1999/xhtml", settings);
        assertFalse(Tag.isKnownTag(tag.getName()));
        assertEquals("unknownTag", tag.getName());
        assertEquals("unknowntag", tag.normalName());
    }

    @Test
    public void testValueOfNormalizedTag() {
        Tag tag = Tag.valueOf("DiV", "http://www.w3.org/1999/xhtml", settings);
        assertTrue(Tag.isKnownTag(tag.getName()));
        assertEquals("DiV", tag.getName());
        assertEquals("div", tag.normalName());
    }

    @Test
    public void testValueOfPreserveTagCase() {
        settings = new ParseSettings(true, false);
        Tag tag = Tag.valueOf("DiV", "http://www.w3.org/1999/xhtml", settings);
        assertTrue(Tag.isKnownTag(tag.getName()));
        assertEquals("DiV", tag.getName());
        assertEquals("div", tag.normalName());
    }

    @Test
    public void testValueOfDifferentNamespace() {
        Tag tag = Tag.valueOf("svg", "http://www.w3.org/2000/svg", settings);
        assertTrue(Tag.isKnownTag(tag.getName()));
        assertEquals("svg", tag.getName());
        assertEquals("svg", tag.normalName());
        assertEquals("http://www.w3.org/2000/svg", tag.namespace());
    }

    @Test
    public void testValueOfEmptyTag() {
        Tag tag = Tag.valueOf("img", "http://www.w3.org/1999/xhtml", settings);
        assertTrue(Tag.isKnownTag(tag.getName()));
        assertEquals("img", tag.getName());
        assertEquals("img", tag.normalName());
        assertTrue(tag.isEmpty());
    }

    @Test
    public void testValueOfBlockTag() {
        Tag tag = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", settings);
        assertTrue(Tag.isKnownTag(tag.getName()));
        assertEquals("div", tag.getName());
        assertEquals("div", tag.normalName());
        assertTrue(tag.isBlock());
    }

    @Test
    public void testValueOfInlineTag() {
        Tag tag = Tag.valueOf("span", "http://www.w3.org/1999/xhtml", settings);
        assertTrue(Tag.isKnownTag(tag.getName()));
        assertEquals("span", tag.getName());
        assertEquals("span", tag.normalName());
        assertFalse(tag.isBlock());
    }
}
