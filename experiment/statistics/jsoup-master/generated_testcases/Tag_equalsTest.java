
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
        Tag tag3Modified = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3Modified.isBlock = true;
        assertFalse(tag1.equals(tag3Modified));
    }

    @Test
    public void testEqualsDifferentFormatAsBlock() {
        Tag tag3Modified = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3Modified.formatAsBlock = false;
        assertFalse(tag1.equals(tag3Modified));
    }

    @Test
    public void testEqualsDifferentEmpty() {
        Tag tag3Modified = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3Modified.empty = true;
        assertFalse(tag1.equals(tag3Modified));
    }

    @Test
    public void testEqualsDifferentSelfClosing() {
        Tag tag3Modified = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3Modified.selfClosing = true;
        assertFalse(tag1.equals(tag3Modified));
    }

    @Test
    public void testEqualsDifferentPreserveWhitespace() {
        Tag tag3Modified = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3Modified.preserveWhitespace = true;
        assertFalse(tag1.equals(tag3Modified));
    }

    @Test
    public void testEqualsDifferentFormList() {
        Tag tag3Modified = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3Modified.formList = true;
        assertFalse(tag1.equals(tag3Modified));
    }

    @Test
    public void testEqualsDifferentFormSubmit() {
        Tag tag3Modified = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3Modified.formSubmit = true;
        assertFalse(tag1.equals(tag3Modified));
    }
}
