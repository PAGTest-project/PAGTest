
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_addTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testAddAttribute() {
        attributes.add("key1", "value1");
        assertTrue(attributes.hasKey("key1"));
        assertEquals("value1", attributes.get("key1"));
    }

    @Test
    public void testAddAttributeWithNullValue() {
        attributes.add("key2", null);
        assertTrue(attributes.hasKey("key2"));
        assertNull(attributes.get("key2"));
    }
}
