
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
    public void testAddAllEmptyIncoming() {
        Attributes incoming = new Attributes();
        attributes.addAll(incoming);
        assertEquals(0, attributes.size());
    }

    @Test
    public void testAddAllNonEmptyIncoming() {
        Attributes incoming = new Attributes();
        incoming.add("key1", "value1");
        incoming.add("key2", "value2");

        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
    }

    @Test
    public void testAddAllWithExistingAttributes() {
        attributes.add("key1", "existingValue1");
        attributes.add("key2", "existingValue2");

        Attributes incoming = new Attributes();
        incoming.add("key1", "newValue1");
        incoming.add("key3", "value3");

        attributes.addAll(incoming);
        assertEquals(3, attributes.size());
        assertEquals("existingValue1", attributes.get("key1")); // Should not overwrite
        assertEquals("existingValue2", attributes.get("key2"));
        assertEquals("value3", attributes.get("key3"));
    }

    @Test
    public void testAddAllWithEmptyAttributes() {
        Attributes incoming = new Attributes();
        incoming.add("key1", "value1");
        incoming.add("key2", "value2");

        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
    }
}
