
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
        assertNotNull(child);
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
        assertTrue(element.childNodeSize() > 0);
        assertEquals(child, element.child(0));
    }

    @Test
    public void testAppendElementWithDefaultNamespace() {
        Element child = element.appendElement("span");
        assertNotNull(child);
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
        assertTrue(element.childNodeSize() > 0);
        assertEquals(child, element.child(0));
    }

    @Test
    public void testAppendElementWithInvalidTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.appendElement("invalidTag!", "http://www.w3.org/1999/xhtml");
        });
    }

    @Test
    public void testAppendElementWithNullTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.appendElement(null, "http://www.w3.org/1999/xhtml");
        });
    }

    @Test
    public void testAppendElementWithNullNamespace() {
        Element child = element.appendElement("span", null);
        assertNotNull(child);
        assertEquals("span", child.tagName());
        assertNull(child.tag().namespace());
        assertTrue(element.childNodeSize() > 0);
        assertEquals(child, element.child(0));
    }
}
