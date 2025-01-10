
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tag_hashCodeTest {
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    public void setUp() {
        tag1 = Tag.valueOf("div", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag2 = Tag.valueOf("span", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3 = Tag.valueOf("div", Parser.NamespaceHtml, ParseSettings.preserveCase);
        tag3.setSelfClosing();
    }

    @Test
    public void testHashCodeEquality() {
        assertEquals(tag1.hashCode(), tag3.hashCode());
    }

    @Test
    public void testHashCodeInequality() {
        assertNotEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentSelfClosing() {
        assertNotEquals(tag1.hashCode(), tag3.hashCode());
    }
}
