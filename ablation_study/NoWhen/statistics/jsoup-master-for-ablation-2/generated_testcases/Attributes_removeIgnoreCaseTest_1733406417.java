
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
        attributes.putIgnoreCase("Key", "Value");
        assertEquals(1, attributes.size());
        assertTrue(attributes.hasKeyIgnoreCase("Key"));

        attributes.removeIgnoreCase("key");
        assertEquals(0, attributes.size());
        assertFalse(attributes.hasKeyIgnoreCase("Key"));
    }

    @Test
    public void testRemoveIgnoreCase_KeyDoesNotExist() {
        attributes.putIgnoreCase("Key", "Value");
        assertEquals(1, attributes.size());
        assertTrue(attributes.hasKeyIgnoreCase("Key"));

        attributes.removeIgnoreCase("NonExistentKey");
        assertEquals(1, attributes.size());
        assertTrue(attributes.hasKeyIgnoreCase("Key"));
    }

    @Test
    public void testRemoveIgnoreCase_CaseInsensitive() {
        attributes.putIgnoreCase("Key", "Value");
        assertEquals(1, attributes.size());
        assertTrue(attributes.hasKeyIgnoreCase("Key"));

        attributes.removeIgnoreCase("kEy");
        assertEquals(0, attributes.size());
        assertFalse(attributes.hasKeyIgnoreCase("Key"));
    }
}
