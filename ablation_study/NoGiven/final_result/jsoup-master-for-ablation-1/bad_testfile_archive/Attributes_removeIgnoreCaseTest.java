
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_removeIgnoreCaseTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.put("Tot", "a&p");
        attributes.put("Hello", "There");
        attributes.put("data-name", "Jsoup");
    }

    @Test
    public void testRemoveIgnoreCaseSuccess() {
        attributes.removeIgnoreCase("tot");
        assertFalse(attributes.hasKeyIgnoreCase("tot"));
        assertEquals("", attributes.getIgnoreCase("tot"));
    }

    @Test
    public void testRemoveIgnoreCaseNotFound() {
        attributes.removeIgnoreCase("nonexistent");
        assertEquals(3, attributes.size());
        assertFalse(attributes.hasKeyIgnoreCase("nonexistent"));
    }

    @Test
    public void testRemoveIgnoreCaseNormalized() {
        attributes.normalize();
        attributes.removeIgnoreCase("tot");
        assertFalse(attributes.hasKeyIgnoreCase("tot"));
        assertEquals("", attributes.getIgnoreCase("tot"));
    }
}
