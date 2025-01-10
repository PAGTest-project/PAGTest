
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
        assertEquals("value1", attributes.get("key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testPutExistingKey() {
        attributes.put("key1", "value1");
        attributes.put("key1", "newValue1");
        assertEquals("newValue1", attributes.get("key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testPutNullValue() {
        attributes.put("key1", null);
        assertNull(attributes.get("key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testPutMultipleKeys() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
        assertEquals(2, attributes.size());
    }

    @Test
    public void testPutWithCapacityCheck() {
        for (int i = 0; i < 5; i++) {
            attributes.put("key" + i, "value" + i);
        }
        assertEquals(5, attributes.size());
        for (int i = 0; i < 5; i++) {
            assertEquals("value" + i, attributes.get("key" + i));
        }
    }
}
