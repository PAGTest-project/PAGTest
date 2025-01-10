
package org.jsoup.nodes;

import org.jsoup.SerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_htmlTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testHtml() {
        attributes.put("Tot", "a&p");
        attributes.put("Hello", "There");
        attributes.put("data-name", "Jsoup");

        String html = attributes.html();
        assertTrue(html.contains("Tot=\"a&amp;p\""));
        assertTrue(html.contains("Hello=\"There\""));
        assertTrue(html.contains("data-name=\"Jsoup\""));
    }

    @Test
    public void testHtmlWithNormalization() {
        attributes.put("Tot", "a&p");
        attributes.put("Hello", "There");
        attributes.put("data-name", "Jsoup");
        attributes.normalize();

        String html = attributes.html();
        assertTrue(html.contains("tot=\"a&amp;p\""));
        assertTrue(html.contains("hello=\"There\""));
        assertTrue(html.contains("data-name=\"Jsoup\""));
    }

    @Test
    public void testHtmlWithCapacityCheck() {
        for (int i = 0; i < 10; i++) {
            attributes.put("key" + i, "value" + i);
        }

        String html = attributes.html();
        for (int i = 0; i < 10; i++) {
            assertTrue(html.contains("key" + i + "=\"value" + i + "\""));
        }
    }

    @Test
    public void testHtmlWithSerializationException() {
        attributes.put("key", "value");
        attributes.put("invalidKey", new Object()); // This will cause an IOException in html()

        assertThrows(SerializationException.class, () -> {
            attributes.html();
        });
    }
}
