
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
        String html = "<body><div id='first'><p>One</p></div><div id='second'><p>Two</p></div></body>";
        doc = Jsoup.parse(html);
    }

    @Test
    public void testGetElementById_ExistingId() {
        Element element = doc.getElementById("first");
        assertNotNull(element);
        assertEquals("first", element.id());
        assertEquals("div", element.tagName());
    }

    @Test
    public void testGetElementById_NonExistingId() {
        Element element = doc.getElementById("nonexistent");
        assertNull(element);
    }

    @Test
    public void testGetElementById_EmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementById("");
        });
    }

    @Test
    public void testGetElementById_NullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementById(null);
        });
    }
}
