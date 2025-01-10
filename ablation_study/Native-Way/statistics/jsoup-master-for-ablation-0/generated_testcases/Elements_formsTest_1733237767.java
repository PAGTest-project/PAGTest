
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_formsTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<form><input name='test'></form><div>Not a form</div>");
        elements = new Elements(doc.body().children());
    }

    @Test
    public void testFormsWithFormElements() {
        List<FormElement> forms = elements.forms();
        assertEquals(1, forms.size());
        assertEquals("form", forms.get(0).tagName());
    }

    @Test
    public void testFormsWithoutFormElements() {
        Document doc = Jsoup.parse("<div>Not a form</div><p>Another non-form</p>");
        elements = new Elements(doc.body().children());
        List<FormElement> forms = elements.forms();
        assertEquals(0, forms.size());
    }

    @Test
    public void testFormsMixedElements() {
        Document doc = Jsoup.parse("<form><input name='test'></form><div>Not a form</div><form><input name='test2'></form>");
        elements = new Elements(doc.body().children());
        List<FormElement> forms = elements.forms();
        assertEquals(2, forms.size());
        assertEquals("form", forms.get(0).tagName());
        assertEquals("form", forms.get(1).tagName());
    }
}
