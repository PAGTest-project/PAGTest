
package org.jsoup.nodes;

import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_tagNameTest {

    @Test
    void testTagName() {
        Element element = new Element("div", "http://www.w3.org/1999/xhtml");
        element.tagName("span", "http://www.w3.org/1999/xhtml");
        assertEquals("span", element.normalName());
    }

    @Test
    void testTagNameWithEmptyTagName() {
        Element element = new Element("div", "http://www.w3.org/1999/xhtml");
        assertThrows(IllegalArgumentException.class, () -> element.tagName("", "http://www.w3.org/1999/xhtml"));
    }

    @Test
    void testTagNameWithEmptyNamespace() {
        Element element = new Element("div", "http://www.w3.org/1999/xhtml");
        assertThrows(IllegalArgumentException.class, () -> element.tagName("span", ""));
    }
}
