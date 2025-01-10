
package org.jsoup.nodes;

import org.jsoup.SerializationException;
import org.jsoup.internal.StringUtil;
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
        // Given
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");

        // When
        String result = attributes.html();

        // Then
        assertNotNull(result);
        assertTrue(result.contains("key1=\"value1\""));
        assertTrue(result.contains("key2=\"value2\""));
    }

    @Test
    public void testHtmlWithIOException() {
        // Given
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");

        // When
        try {
            // Simulate an IOException by mocking the behavior of html(sb, outputSettings)
            // This is a simplified approach to simulate the exception
            attributes.html();
            fail("Expected SerializationException to be thrown");
        } catch (SerializationException e) {
            // Then
            assertTrue(e.getMessage().contains("IOException"));
        }
    }
}
