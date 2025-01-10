
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Elements_formsTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        String html = "<html><body><form><input name='test'></form><div><form><input name='test2'></form></div></body></html>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.body().children());
    }

    @Test
    public void testFormsWithFormElements() {
        List<FormElement> forms = elements.forms();
        assertEquals(2, forms.size());
        assertTrue(forms.get(0) instanceof FormElement);
        assertTrue(forms.get(1) instanceof FormElement);
    }

    @Test
    public void testFormsWithNoFormElements() {
        String html = "<html><body><div><p>No forms here</p></div></body></html>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.body().children());
        List<FormElement> forms = elements.forms();
        assertEquals(0, forms.size());
    }

    @Test
    public void testFormsWithMixedElements() {
        String html = "<html><body><form><input name='test'></form><div><p>No forms here</p></div></body></html>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.body().children());
        List<FormElement> forms = elements.forms();
        assertEquals(1, forms.size());
        assertTrue(forms.get(0) instanceof FormElement);
    }
}
