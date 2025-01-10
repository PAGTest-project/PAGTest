
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
        element.attr("class", "test");
        Element clone = element.shallowClone();
        assertEquals("http://example.com", clone.baseUri());
        assertEquals("test", clone.attr("class"));
    }

    @Test
    public void testShallowCloneWithoutBaseUri() {
        element.attr("class", "test");
        element.removeAttr("href");
        Element clone = element.shallowClone();
        assertNull(clone.baseUri());
        assertEquals("test", clone.attr("class"));
    }

    @Test
    public void testShallowCloneWithAttributes() {
        element.attr("class", "test");
        element.attr("id", "testId");
        Element clone = element.shallowClone();
        assertEquals("test", clone.attr("class"));
        assertEquals("testId", clone.attr("id"));
    }

    @Test
    public void testShallowCloneWithoutAttributes() {
        element.clearAttributes();
        Element clone = element.shallowClone();
        assertTrue(clone.attributes().isEmpty());
    }

    @Test
    public void testShallowCloneWithEmptyBaseUri() {
        element.attr("href", "");
        Element clone = element.shallowClone();
        assertNull(clone.baseUri());
    }
}
