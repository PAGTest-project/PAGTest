
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_cloneTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
    }

    @Test
    public void testClone() {
        Attributes clonedAttributes = attributes.clone();

        // Verify size and individual keys/values
        assertEquals(attributes.size(), clonedAttributes.size());
        assertEquals(attributes.get("key1"), clonedAttributes.get("key1"));
        assertEquals(attributes.get("key2"), clonedAttributes.get("key2"));

        // Modify original and verify clone remains unchanged
        attributes.put("key3", "value3");
        assertNotEquals(attributes.size(), clonedAttributes.size());
        assertFalse(clonedAttributes.hasKey("key3"));
    }
}
