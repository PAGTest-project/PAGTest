
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_removeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testRemoveExistingKey() {
        attributes.put("key1", "value1");
        attributes.remove("key1");
        assertFalse(attributes.hasKey("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        attributes.remove("nonExistingKey");
        assertFalse(attributes.hasKey("nonExistingKey"));
    }

    @Test
    public void testRemoveIgnoreCaseExistingKey() {
        attributes.put("Key1", "value1");
        attributes.removeIgnoreCase("key1");
        assertFalse(attributes.hasKey("Key1"));
    }

    @Test
    public void testRemoveIgnoreCaseNonExistingKey() {
        attributes.removeIgnoreCase("nonExistingKey");
        assertFalse(attributes.hasKey("nonExistingKey"));
    }

    @Test
    public void testRemoveWithIndex() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        attributes.remove(0);
        assertFalse(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
    }

    @Test
    public void testRemoveWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> attributes.remove(1));
    }

    @Test
    public void testRemoveWithEmptyAttributes() {
        attributes.remove("key1");
        assertFalse(attributes.hasKey("key1"));
    }

    @Test
    public void testRemoveWithMultipleKeys() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        attributes.put("key3", "value3");
        attributes.remove("key2");
        assertFalse(attributes.hasKey("key2"));
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key3"));
    }
}
