
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_putTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testPutNewKey() {
        attributes.put("key1", "value1");
        assertTrue(attributes.hasKey("key1"));
        assertEquals("value1", attributes.get("key1"));
    }

    @Test
    public void testPutExistingKey() {
        attributes.put("key1", "value1");
        attributes.put("key1", "newValue1");
        assertTrue(attributes.hasKey("key1"));
        assertEquals("newValue1", attributes.get("key1"));
    }

    @Test
    public void testPutNullValue() {
        attributes.put("key1", null);
        assertTrue(attributes.hasKey("key1"));
        assertNull(attributes.get("key1"));
    }

    @Test
    public void testPutMultipleKeys() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
    }

    @Test
    public void testPutWithRemove() {
        attributes.put("key1", "value1");
        attributes.remove("key1");
        attributes.put("key1", "newValue1");
        assertTrue(attributes.hasKey("key1"));
        assertEquals("newValue1", attributes.get("key1"));
    }
}
