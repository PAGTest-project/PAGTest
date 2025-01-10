
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
        Tag tag = Tag.valueOf("p", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("p", tag.getName());
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
    public void testValueOfPreserveTagCase() {
        settings = new ParseSettings(true, false);
        Tag tag = Tag.valueOf("P", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("P", tag.getName());
        assertEquals("http://www.w3.org/1999/xhtml", tag.namespace());
    }

    @Test
    public void testValueOfNormalizeTag() {
        settings = new ParseSettings(false, false);
        Tag tag = Tag.valueOf("   p   ", "http://www.w3.org/1999/xhtml", settings);
        assertNotNull(tag);
        assertEquals("p", tag.getName());
        assertEquals("http://www.w3.org/1999/xhtml", tag.namespace());
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
            Tag.valueOf("p", null, settings);
        });
    }
}
