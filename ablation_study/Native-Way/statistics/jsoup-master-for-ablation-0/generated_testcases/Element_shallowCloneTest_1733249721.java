
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_shallowCloneTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element(Tag.valueOf("div"), "http://example.com");
    }

    @Test
    public void testShallowCloneWithBaseUri() {
        element.attr("id", "test");
        Element clone = element.shallowClone();
        assertEquals("http://example.com", clone.baseUri());
        assertEquals("test", clone.id());
    }

    @Test
    public void testShallowCloneWithoutBaseUri() {
        element.attr("id", "test");
        element.removeAttr("baseUri");
        Element clone = element.shallowClone();
        assertNull(clone.baseUri());
        assertEquals("test", clone.id());
    }

    @Test
    public void testShallowCloneWithAttributes() {
        element.attr("id", "test");
        element.attr("class", "testClass");
        Element clone = element.shallowClone();
        assertEquals("test", clone.id());
        assertEquals("testClass", clone.className());
    }

    @Test
    public void testShallowCloneWithoutAttributes() {
        Element clone = element.shallowClone();
        assertNull(clone.id());
        assertNull(clone.className());
    }
}
