
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tag_equalsTest {
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    public void setUp() {
        tag1 = Tag.valueOf("img");
        tag2 = Tag.valueOf("div");
        tag3 = Tag.valueOf("img");
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(tag1.equals(tag1));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(tag1.equals("img"));
    }

    @Test
    public void testEqualsDifferentTags() {
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void testEqualsSameTags() {
        assertTrue(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentTagName() {
        Tag tag4 = Tag.valueOf("img");
        tag4.tagName = "image";
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentEmpty() {
        Tag tag4 = Tag.valueOf("img");
        tag4.empty = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentFormatAsBlock() {
        Tag tag4 = Tag.valueOf("img");
        tag4.formatAsBlock = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentIsBlock() {
        Tag tag4 = Tag.valueOf("img");
        tag4.isBlock = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentPreserveWhitespace() {
        Tag tag4 = Tag.valueOf("img");
        tag4.preserveWhitespace = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentSelfClosing() {
        Tag tag4 = Tag.valueOf("img");
        tag4.selfClosing = false;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentFormList() {
        Tag tag4 = Tag.valueOf("img");
        tag4.formList = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentFormSubmit() {
        Tag tag4 = Tag.valueOf("img");
        tag4.formSubmit = true;
        assertFalse(tag1.equals(tag4));
    }
}
