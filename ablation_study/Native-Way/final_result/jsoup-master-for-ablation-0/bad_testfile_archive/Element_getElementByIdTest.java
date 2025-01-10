
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementByIdTest {

    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div id='test'><p id='p1'>One</p><p id='p2'>Two</p></div>");
    }

    @Test
    public void testGetElementByIdFound() {
        Element element = doc.getElementById("p1");
        assertNotNull(element);
        assertEquals("p1", element.id());
        assertEquals("One", element.text());
    }

    @Test
    public void testGetElementByIdNotFound() {
        Element element = doc.getElementById("nonexistent");
        assertNull(element);
    }

    @Test
    public void testGetElementByIdEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementById("");
        });
    }

    @Test
    public void testGetElementByIdNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementById(null);
        });
    }
}
