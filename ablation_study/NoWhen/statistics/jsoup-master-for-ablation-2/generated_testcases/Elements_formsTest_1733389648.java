
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
        FormElement form1 = new FormElement("form");
        FormElement form2 = new FormElement("form");
        elements.add(new Element("div"));
        elements.add(form1);
        elements.add(new Element("span"));
        elements.add(form2);
        List<FormElement> forms = elements.forms();
        assertEquals(2, forms.size());
        assertTrue(forms.contains(form1));
        assertTrue(forms.contains(form2));
    }

    @Test
    public void testFormsWithMixedElements() {
        FormElement form1 = new FormElement("form");
        Element div = new Element("div");
        FormElement form2 = new FormElement("form");
        elements.add(form1);
        elements.add(div);
        elements.add(form2);
        List<FormElement> forms = elements.forms();
        assertEquals(2, forms.size());
        assertTrue(forms.contains(form1));
        assertTrue(forms.contains(form2));
        assertFalse(forms.contains(div));
    }

    @Test
    public void testFormsWithEmptyElements() {
        List<FormElement> forms = elements.forms();
        assertTrue(forms.isEmpty());
    }
}
