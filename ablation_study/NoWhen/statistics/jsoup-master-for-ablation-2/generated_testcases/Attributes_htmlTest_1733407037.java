
package org.jsoup.nodes;

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

        assertEquals(3, attributes.size());
        assertTrue(attributes.hasKey("Tot"));
        assertTrue(attributes.hasKey("Hello"));
        assertTrue(attributes.hasKey("data-name"));
        assertFalse(attributes.hasKey("tot"));
        assertTrue(attributes.hasKeyIgnoreCase("tot"));
        assertEquals("There", attributes.getIgnoreCase("hEllo"));

        Map<String, String> dataset = attributes.dataset();
        assertEquals(1, dataset.size());
        assertEquals("Jsoup", dataset.get("name"));
        assertEquals("", attributes.get("tot"));
        assertEquals("a&p", attributes.get("Tot"));
        assertEquals("a&p", attributes.getIgnoreCase("tot"));

        assertEquals(" Tot=\"a&amp;p\" Hello=\"There\" data-name=\"Jsoup\"", attributes.html());
        assertEquals(attributes.html(), attributes.toString());
    }

    @Test
    public void testHtmlWithEmptyAttributes() {
        assertEquals("", attributes.html());
        assertEquals(attributes.html(), attributes.toString());
    }

    @Test
    public void testHtmlWithSpecialCharacters() {
        attributes.put("key1", "value1&");
        attributes.put("key2", "value2<");
        attributes.put("key3", "value3>");

        assertEquals(" key1=\"value1&amp;\" key2=\"value2&lt;\" key3=\"value3&gt;\"", attributes.html());
        assertEquals(attributes.html(), attributes.toString());
    }

    @Test
    public void testHtmlWithLargeAttributes() {
        StringBuilder largeValue = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeValue.append("a");
        }
        attributes.put("largeKey", largeValue.toString());

        assertEquals(" largeKey=\"" + largeValue.toString() + "\"", attributes.html());
        assertEquals(attributes.html(), attributes.toString());
    }
}
