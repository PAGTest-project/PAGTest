
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_baseUriTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("a");
    }

    @Test
    public void testBaseUriWithSetBaseUri() {
        element.setBaseUri("http://example.com");
        assertEquals("http://example.com", element.baseUri());
    }

    @Test
    public void testBaseUriWithoutSetBaseUri() {
        assertEquals("", element.baseUri());
    }

    @Test
    public void testBaseUriWithParent() {
        Element parent = new Element("div");
        parent.setBaseUri("http://parent.com");
        element.attr("baseUri", "http://child.com");
        parent.appendChild(element);
        assertEquals("http://child.com", element.baseUri());
    }
}
