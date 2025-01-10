
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.helper.ValidationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendElementTest {
    private Element parentElement;

    @BeforeEach
    public void setUp() {
        parentElement = new Element("div");
        parentElement.setBaseUri("http://example.com");
    }

    @Test
    public void testAppendElement() {
        Element child = parentElement.appendElement("span", "http://www.w3.org/1999/xhtml");
        assertNotNull(child);
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
        assertEquals(1, parentElement.childNodeSize());
        assertEquals(child, parentElement.child(0));
    }

    @Test
    public void testAppendElementWithDefaultNamespace() {
        Element child = parentElement.appendElement("span");
        assertNotNull(child);
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
        assertEquals(1, parentElement.childNodeSize());
        assertEquals(child, parentElement.child(0));
    }

    @Test
    public void testAppendElementWithDifferentNamespace() {
        Element child = parentElement.appendElement("customTag", "http://custom.namespace.com");
        assertNotNull(child);
        assertEquals("customTag", child.tagName());
        assertEquals("http://custom.namespace.com", child.tag().namespace());
        assertEquals(1, parentElement.childNodeSize());
        assertEquals(child, parentElement.child(0));
    }

    @Test
    public void testAppendElementWithInvalidTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            parentElement.appendElement("invalidTag!", "http://www.w3.org/1999/xhtml");
        });
        assertEquals(0, parentElement.childNodeSize());
    }

    @Test
    public void testAppendElementWithNullTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            parentElement.appendElement(null, "http://www.w3.org/1999/xhtml");
        });
        assertEquals(0, parentElement.childNodeSize());
    }

    @Test
    public void testAppendElementWithNullNamespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            parentElement.appendElement("span", null);
        });
        assertEquals(0, parentElement.childNodeSize());
    }
}
