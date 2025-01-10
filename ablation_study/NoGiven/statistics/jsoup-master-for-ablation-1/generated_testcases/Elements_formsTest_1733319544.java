
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_formsTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testFormsWithNoFormElements() {
        elements.add(new Element("div"));
        elements.add(new Element("span"));
        List<FormElement> forms = elements.forms();
        assertTrue(forms.isEmpty());
    }

    @Test
    public void testFormsWithFormElements() {
        elements.add(new Element("div"));
        elements.add(new FormElement("form"));
        elements.add(new Element("span"));
        elements.add(new FormElement("form"));
        List<FormElement> forms = elements.forms();
        assertEquals(2, forms.size());
    }

    @Test
    public void testFormsWithMixedElements() {
        elements.add(new Element("div"));
        elements.add(new FormElement("form"));
        elements.add(new Element("span"));
        List<FormElement> forms = elements.forms();
        assertEquals(1, forms.size());
    }

    @Test
    public void testFormsWithDocument() {
        Document doc = Jsoup.parse("<html><body><form></form><div></div><form></form></body></html>");
        elements = doc.body().children();
        List<FormElement> forms = elements.forms();
        assertEquals(2, forms.size());
    }
}
