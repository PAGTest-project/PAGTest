
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormElement_elementsTest {

    private FormElement formElement;

    @BeforeEach
    public void setUp() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        formElement = ((FormElement) doc.select("form").first());
    }

    @Test
    public void testElementsWithNoLinkedElements() {
        Elements elements = formElement.elements();
        assertEquals(1, elements.size());
        assertEquals("input", elements.first().tagName());
    }

    @Test
    public void testElementsWithLinkedElements() {
        Element linkedElement = new Element("input").attr("name", "linked");
        formElement.addElement(linkedElement);

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains(linkedElement));
    }

    @Test
    public void testElementsWithDuplicateLinkedElements() {
        Element linkedElement = new Element("input").attr("name", "linked");
        formElement.addElement(linkedElement);
        formElement.addElement(linkedElement);

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains(linkedElement));
    }

    @Test
    public void testElementsWithRemovedLinkedElements() {
        Element linkedElement = new Element("input").attr("name", "linked");
        formElement.addElement(linkedElement);
        linkedElement.remove();

        Elements elements = formElement.elements();
        assertEquals(1, elements.size());
        assertFalse(elements.contains(linkedElement));
    }
}
