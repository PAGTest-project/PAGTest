
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
        incoming.add("key1", "value1");
        incoming.add("key2", "value2");

        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
    }

    @Test
    public void testAddAllWithExistingAttributes() {
        attributes.add("key1", "value1");
        Attributes incoming = new Attributes();
        incoming.add("key2", "value2");

        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
    }

    @Test
    public void testAddAllWithDuplicateKeys() {
        attributes.add("key1", "value1");
        Attributes incoming = new Attributes();
        incoming.add("key1", "newValue1");
        incoming.add("key2", "value2");

        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
        assertEquals("newValue1", attributes.get("key1"));
    }
}
