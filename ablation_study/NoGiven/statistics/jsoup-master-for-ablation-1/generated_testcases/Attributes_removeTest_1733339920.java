
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_removeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.add("Key1", "Val1");
        attributes.add("Key2", "Val2");
    }

    @Test
    public void testRemoveExistingKey() {
        assertTrue(attributes.hasKey("Key1"));
        assertEquals(2, attributes.size());

        attributes.remove("Key1");

        assertFalse(attributes.hasKey("Key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertFalse(attributes.hasKey("Key3"));
        assertEquals(2, attributes.size());

        attributes.remove("Key3");

        assertFalse(attributes.hasKey("Key3"));
        assertEquals(2, attributes.size());
    }

    @Test
    public void testRemoveWithCheckCapacity() {
        for (int i = 3; i <= 100; i++) {
            attributes.add("Key" + i, "Val" + i);
        }

        assertTrue(attributes.hasKey("Key1"));
        assertEquals(100, attributes.size());

        attributes.remove("Key1");

        assertFalse(attributes.hasKey("Key1"));
        assertEquals(99, attributes.size());
    }
}
