
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attributes_removeTest {

    @Test
    void testRemoveWithExistingKey() {
        Attributes attributes = new Attributes();
        attributes.put("key1", "value1");
        attributes.remove("key1");
        assertFalse(attributes.hasKey("key1"));
    }

    @Test
    void testRemoveWithNonExistingKey() {
        Attributes attributes = new Attributes();
        attributes.remove("nonExistingKey");
        assertFalse(attributes.hasKey("nonExistingKey"));
    }
}
