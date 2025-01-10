
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
        formElement = new FormElement(Tag.valueOf("form"), null, null);
    }

    @Test
    public void testElementsWithNoLinkedElements() {
        Elements elements = formElement.elements();
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testElementsWithLinkedElements() {
        Element element1 = new Element(Tag.valueOf("input"), null);
        Element element2 = new Element(Tag.valueOf("input"), null);
        formElement.addElement(element1);
        formElement.addElement(element2);

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains(element1));
        assertTrue(elements.contains(element2));
    }

    @Test
    public void testElementsWithRemovedLinkedElement() {
        Element element1 = new Element(Tag.valueOf("input"), null);
        Element element2 = new Element(Tag.valueOf("input"), null);
        formElement.addElement(element1);
        formElement.addElement(element2);
        formElement.removeChild(element1);

        Elements elements = formElement.elements();
        assertEquals(1, elements.size());
        assertTrue(elements.contains(element2));
        assertFalse(elements.contains(element1));
    }

    @Test
    public void testElementsWithFormChildren() {
        String html = "<form><input name='one' value='two'><select name='three'><option value='not'>" +
                "<option value='four' selected><option value='five' selected></select></form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.stream().anyMatch(el -> el.attr("name").equals("one")));
        assertTrue(elements.stream().anyMatch(el -> el.attr("name").equals("three")));
    }

    @Test
    public void testElementsWithFormChildrenAndLinkedElements() {
        String html = "<form><input name='one' value='two'></form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();

        Element element1 = new Element(Tag.valueOf("input"), null);
        formElement.addElement(element1);

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.stream().anyMatch(el -> el.attr("name").equals("one")));
        assertTrue(elements.contains(element1));
    }
}
