
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
        String html = "<form><input name='one' value='two'><select name='three'><option value='not'>" +
                "<option value='four' selected><option value='five' selected><textarea name=six>seven</textarea>" +
                "<input name='seven' type='radio' value='on' checked><input name='seven' type='radio' value='off'>" +
                "<input name='eight' type='checkbox' checked><input name='nine' type='checkbox' value='unset'>" +
                "<input name='ten' value='text' disabled>" +
                "<input name='eleven' value='text' type='button'>" +
                "<input name='twelve' value='text' type='image'>" +
                "</form>";
        Document doc = Jsoup.parse(html);
        formElement = (FormElement) doc.select("form").first();
    }

    @Test
    public void testElementsWithFormChildrenOnly() {
        Elements elements = formElement.elements();
        assertEquals(12, elements.size());
    }

    @Test
    public void testElementsWithLinkedElements() {
        Element linkedElement = new Element("input").attr("name", "linked").attr("value", "linkedValue");
        formElement.addElement(linkedElement);
        Elements elements = formElement.elements();
        assertEquals(13, elements.size());
        assertTrue(elements.contains(linkedElement));
    }

    @Test
    public void testElementsWithLinkedElementsNotInDOM() {
        Element linkedElement = new Element("input").attr("name", "linked").attr("value", "linkedValue");
        formElement.addElement(linkedElement);
        linkedElement.remove();
        Elements elements = formElement.elements();
        assertEquals(12, elements.size());
        assertFalse(elements.contains(linkedElement));
    }
}
