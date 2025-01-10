
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_expectFormTest {
    private Document document;

    @BeforeEach
    public void setUp() {
        String html = "<html><body><form id='form1'></form><div id='notForm'></div></body></html>";
        document = Jsoup.parse(html);
    }

    @Test
    public void testExpectFormSuccess() {
        FormElement formElement = document.expectForm("#form1");
        assertNotNull(formElement);
        assertEquals("form1", formElement.id());
    }

    @Test
    public void testExpectFormValidationFailed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            document.expectForm("#notForm");
        });
        String expectedMessage = "No form elements matched the query '#notForm' in the document.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
