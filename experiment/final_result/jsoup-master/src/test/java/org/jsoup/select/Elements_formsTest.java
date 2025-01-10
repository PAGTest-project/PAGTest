
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
        elements = new Elements();
    }

    @Test
    public void testForms() {
        // Given
        Document doc = Jsoup.parse("<form><input name='test'></form><div>Not a form</div>");
        elements.addAll(doc.body().children());

        // When
        List<FormElement> forms = elements.forms();

        // Then
        assertEquals(1, forms.size());
        assertEquals("form", forms.get(0).tagName());
    }
}
