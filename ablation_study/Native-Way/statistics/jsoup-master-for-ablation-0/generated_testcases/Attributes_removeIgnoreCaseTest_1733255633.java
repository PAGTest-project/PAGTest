
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_removeIgnoreCaseTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testRemoveIgnoreCase_KeyExists() {
        attributes.put("Key", "Value");
        attributes.put("kEy", "Value2");
        attributes.put("KEY", "Value3");

        attributes.removeIgnoreCase("key");

        assertEquals(1, attributes.size());
        assertFalse(attributes.hasKeyIgnoreCase("Key"));
        assertFalse(attributes.hasKeyIgnoreCase("kEy"));
        assertTrue(attributes.hasKeyIgnoreCase("KEY"));
    }

    @Test
    public void testRemoveIgnoreCase_KeyDoesNotExist() {
        attributes.put("Key", "Value");
        attributes.put("AnotherKey", "AnotherValue");

        attributes.removeIgnoreCase("nonexistent");

        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKeyIgnoreCase("Key"));
        assertTrue(attributes.hasKeyIgnoreCase("AnotherKey"));
    }

    @Test
    public void testRemoveIgnoreCase_EmptyAttributes() {
        attributes.removeIgnoreCase("key");

        assertEquals(0, attributes.size());
    }

    @Test
    public void testRemoveIgnoreCase_MultipleRemovals() {
        attributes.put("Key1", "Value1");
        attributes.put("Key2", "Value2");
        attributes.put("Key3", "Value3");

        attributes.removeIgnoreCase("key1");
        attributes.removeIgnoreCase("key2");

        assertEquals(1, attributes.size());
        assertFalse(attributes.hasKeyIgnoreCase("Key1"));
        assertFalse(attributes.hasKeyIgnoreCase("Key2"));
        assertTrue(attributes.hasKeyIgnoreCase("Key3"));
    }

    @Test
    public void testRemoveIgnoreCase_CaseInsensitiveRemoval() {
        attributes.put("Key", "Value");
        attributes.put("kEy", "Value2");
        attributes.put("KEY", "Value3");

        attributes.removeIgnoreCase("kEy");

        assertEquals(1, attributes.size());
        assertFalse(attributes.hasKeyIgnoreCase("Key"));
        assertFalse(attributes.hasKeyIgnoreCase("kEy"));
        assertTrue(attributes.hasKeyIgnoreCase("KEY"));
    }
}
