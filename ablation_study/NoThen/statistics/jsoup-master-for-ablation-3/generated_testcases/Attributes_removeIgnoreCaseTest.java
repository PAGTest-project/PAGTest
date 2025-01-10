
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attributes_removeIgnoreCaseTest {

    @Test
    void testRemoveIgnoreCase_KeyFound() {
        Attributes attributes = new Attributes();
        attributes.put("key1", "value1");
        attributes.removeIgnoreCase("key1");
        assertFalse(attributes.hasKey("key1"));
    }

    @Test
    void testRemoveIgnoreCase_KeyNotFound() {
        Attributes attributes = new Attributes();
        attributes.put("key1", "value1");
        attributes.removeIgnoreCase("key2");
        assertTrue(attributes.hasKey("key1"));
    }
}
