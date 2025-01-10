
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
        assertEquals("http://www.w3.org/1999/xhtml", tag.namespace());
    }

    @Test
    public void testValueOfUnknownTag() {
        Tag tag = Tag.valueOf("unknownTag", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("unknownTag", tag.getName());
        assertEquals("http://www.w3.org/1999/xhtml", tag.namespace());
        assertFalse(tag.isBlock());
    }

    @Test
    public void testValueOfWithPreserveTagCase() {
        settings = new ParseSettings(true, false);
        Tag tag = Tag.valueOf("Div", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("Div", tag.getName());
        assertEquals("http://www.w3.org/1999/xhtml", tag.namespace());
    }

    @Test
    public void testValueOfWithNormalizedTag() {
        settings = new ParseSettings(false, false);
        Tag tag = Tag.valueOf("Div", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("div", tag.getName());
        assertEquals("http://www.w3.org/1999/xhtml", tag.namespace());
    }

    @Test
    public void testValueOfWithNullNamespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tag.valueOf("div", null, settings);
        });
    }

    @Test
    public void testValueOfWithEmptyTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tag.valueOf("", "http://www.w3.org/1999/xhtml", settings);
        });
    }
}
