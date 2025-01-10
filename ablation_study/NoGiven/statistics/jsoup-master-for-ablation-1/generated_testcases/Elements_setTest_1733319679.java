
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_setTest {

    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p id='p1'>Paragraph 1</p><p id='p2'>Paragraph 2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testSetElementSuccess() {
        Element newElement = new Element("p").attr("id", "p3").text("New Paragraph");
        Element oldElement = elements.set(0, newElement);

        assertEquals("p1", oldElement.id());
        assertEquals("p3", elements.get(0).id());
        assertEquals("New Paragraph", elements.get(0).text());
    }

    @Test
    public void testSetElementNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            elements.set(0, null);
        });
    }

    @Test
    public void testSetElementOutOfBounds() {
        Element newElement = new Element("p").attr("id", "p3").text("New Paragraph");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            elements.set(elements.size(), newElement);
        });
    }

    @Test
    public void testSetElementReplaceWith() {
        Element newElement = new Element("p").attr("id", "p3").text("New Paragraph");
        Element oldElement = elements.set(0, newElement);

        assertEquals("p1", oldElement.id());
        assertEquals("p3", elements.get(0).id());
        assertEquals("New Paragraph", elements.get(0).text());

        // Verify that the old element's replaceWith method was called
        assertEquals(newElement, elements.get(0).nextElementSibling());
    }
}
