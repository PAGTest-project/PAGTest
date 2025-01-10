
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_hasDeclaredValueForKeyIgnoreCaseTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testHasDeclaredValueForKeyIgnoreCase_ValueExists() {
        attributes.putIgnoreCase("key1", "value1");
        assertTrue(attributes.hasDeclaredValueForKeyIgnoreCase("key1"));
        assertTrue(attributes.hasDeclaredValueForKeyIgnoreCase("KEY1"));
    }

    @Test
    public void testHasDeclaredValueForKeyIgnoreCase_ValueDoesNotExist() {
        attributes.putIgnoreCase("key2", null);
        assertFalse(attributes.hasDeclaredValueForKeyIgnoreCase("key2"));
        assertFalse(attributes.hasDeclaredValueForKeyIgnoreCase("KEY2"));
    }

    @Test
    public void testHasDeclaredValueForKeyIgnoreCase_KeyDoesNotExist() {
        assertFalse(attributes.hasDeclaredValueForKeyIgnoreCase("nonexistentKey"));
    }
}
