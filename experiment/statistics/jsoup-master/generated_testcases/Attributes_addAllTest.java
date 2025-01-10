
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_addAllTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testAddAllWithEmptyAttributes() {
        Attributes incoming = new Attributes();
        attributes.addAll(incoming);
        assertEquals(0, attributes.size());
    }

    @Test
    public void testAddAllWithNonEmptyAttributes() {
        Attributes incoming = new Attributes();
        incoming.put("key1", "value1");
        incoming.put("key2", "value2");

        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
    }

    @Test
    public void testAddAllWithExistingAttributes() {
        attributes.put("key1", "existingValue1");
        Attributes incoming = new Attributes();
        incoming.put("key1", "newValue1");
        incoming.put("key2", "value2");

        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertEquals("newValue1", attributes.get("key1"));
        assertTrue(attributes.hasKey("key2"));
    }
}
