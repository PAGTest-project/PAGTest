
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_hasDeclaredValueForKeyTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testHasDeclaredValueForKey_KeyExistsWithValue() {
        attributes.put("key1", "value1");
        assertTrue(attributes.hasDeclaredValueForKey("key1"));
    }

    @Test
    public void testHasDeclaredValueForKey_KeyExistsWithoutValue() {
        attributes.put("key2", null);
        assertFalse(attributes.hasDeclaredValueForKey("key2"));
    }

    @Test
    public void testHasDeclaredValueForKey_KeyDoesNotExist() {
        assertFalse(attributes.hasDeclaredValueForKey("nonexistentKey"));
    }
}
