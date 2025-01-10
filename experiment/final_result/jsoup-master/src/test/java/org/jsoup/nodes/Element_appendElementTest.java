
package org.jsoup.nodes;

import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendElementTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAppendElement() {
        Element child = element.appendElement("span", "http://www.w3.org/1999/xhtml");
        assertEquals(1, element.childNodeSize());
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
    }
}
