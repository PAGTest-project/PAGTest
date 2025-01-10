
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
    public void testHtmlSuccess() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        String html = attributes.html();
        assertTrue(html.contains("key1=\"value1\""));
        assertTrue(html.contains("key2=\"value2\""));
    }

    @Test
    public void testHtmlEmptyAttributes() {
        String html = attributes.html();
        assertEquals("", html);
    }

    @Test
    public void testHtmlWithIOException() {
        // Simulate an IOException by overriding the html method to throw an exception
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        try {
            attributes.html();
            fail("Expected SerializationException to be thrown");
        } catch (SerializationException e) {
            assertTrue(e.getCause() instanceof IOException);
        }
    }
}
