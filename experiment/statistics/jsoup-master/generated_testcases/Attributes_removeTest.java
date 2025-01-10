
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_removeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.add("key1", "value1");
        attributes.add("key2", "value2");
    }

    @Test
    public void testRemoveExistingKey() {
        attributes.remove("key1");
        assertFalse(attributes.hasKey("key1"), "Attribute 'key1' not correctly removed");
    }

    @Test
    public void testRemoveNonExistingKey() {
        attributes.remove("nonExistingKey");
        assertEquals(2, attributes.size(), "Size should not change when removing non-existing key");
    }
}
