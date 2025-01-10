
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
        tag1 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag2 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag3 = Tag.valueOf("span", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(tag1.equals(tag1));
    }

    @Test
    public void testEqualsDifferentObjectsSameContent() {
        assertTrue(tag1.equals(tag2));
    }

    @Test
    public void testEqualsDifferentObjectsDifferentContent() {
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(tag1.equals("div"));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(tag1.equals(null));
    }

    @Test
    public void testEqualsDifferentTagName() {
        Tag tag4 = Tag.valueOf("p", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentNamespace() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/2000/svg", ParseSettings.preserveCase);
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentIsBlock() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag4.isBlock = false;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentFormatAsBlock() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag4.formatAsBlock = false;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentEmpty() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag4.empty = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentSelfClosing() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag4.selfClosing = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentPreserveWhitespace() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag4.preserveWhitespace = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentFormList() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag4.formList = true;
        assertFalse(tag1.equals(tag4));
    }

    @Test
    public void testEqualsDifferentFormSubmit() {
        Tag tag4 = Tag.valueOf("div", "http://www.w3.org/1999/xhtml", ParseSettings.preserveCase);
        tag4.formSubmit = true;
        assertFalse(tag1.equals(tag4));
    }
}
