
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
        attributes.removeIgnoreCase("key");
        assertFalse(attributes.hasKeyIgnoreCase("Key"));
    }

    @Test
    public void testRemoveIgnoreCase_KeyDoesNotExist() {
        attributes.putIgnoreCase("Key", "Value");
        attributes.removeIgnoreCase("nonexistent");
        assertTrue(attributes.hasKeyIgnoreCase("Key"));
    }
}
