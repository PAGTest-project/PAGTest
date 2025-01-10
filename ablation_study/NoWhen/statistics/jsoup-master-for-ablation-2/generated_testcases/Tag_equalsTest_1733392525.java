
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
    public void testEquals_SameObject() {
        assertTrue(tag1.equals(tag1));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(tag1.equals("div"));
    }

    @Test
    public void testEquals_SameAttributes() {
        assertTrue(tag1.equals(tag2));
    }

    @Test
    public void testEquals_DifferentTagName() {
        assertFalse(tag1.equals(tag3));
    }

    @Test
    public void testEquals_DifferentIsBlock() {
        tag2.isBlock = false;
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void testEquals_DifferentFormatAsBlock() {
        tag2.formatAsBlock = false;
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void testEquals_DifferentEmpty() {
        tag2.empty = true;
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void testEquals_DifferentSelfClosing() {
        tag2.selfClosing = true;
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void testEquals_DifferentPreserveWhitespace() {
        tag2.preserveWhitespace = true;
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void testEquals_DifferentFormList() {
        tag2.formList = true;
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void testEquals_DifferentFormSubmit() {
        tag2.formSubmit = true;
        assertFalse(tag1.equals(tag2));
    }
}
