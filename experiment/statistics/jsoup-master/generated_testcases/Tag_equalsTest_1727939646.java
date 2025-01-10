
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
        tag1 = Tag.valueOf("div", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag2 = Tag.valueOf("div", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3 = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(tag1.equals(tag1));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(tag1.equals("not a tag"));
    }

    @Test
    public void testEqualsSameAttributes() {
        assertTrue(tag1.equals(tag2));
    }

    @Test
    public void testEqualsDifferentTagName() {
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentIsBlock() {
        tag3.isBlock = true;
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentFormatAsBlock() {
        tag3.formatAsBlock = false;
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentEmpty() {
        tag3.empty = true;
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentSelfClosing() {
        tag3.selfClosing = true;
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentPreserveWhitespace() {
        tag3.preserveWhitespace = true;
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentFormList() {
        tag3.formList = true;
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEqualsDifferentFormSubmit() {
        tag3.formSubmit = true;
        assertFalse(tag1.equals(tag3));
    }
}
