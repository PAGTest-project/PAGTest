
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
    }

    @Test
    public void testPutExistingKey() {
        attributes.put("key1", "value1");
        attributes.put("key1", "newValue1");
        assertEquals("newValue1", attributes.get("key1"));
    }

    @Test
    public void testPutNullKey() {
        assertThrows(IllegalArgumentException.class, () -> attributes.put(null, "value1"));
    }
}
