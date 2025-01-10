
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attributes_cloneTest {

    @Test
    void testClone() {
        Attributes original = new Attributes();
        original.add("key1", "value1");
        original.add("key2", "value2");

        Attributes cloned = original.clone();

        assertEquals(original.size(), cloned.size());
        assertEquals(original.get("key1"), cloned.get("key1"));
        assertEquals(original.get("key2"), cloned.get("key2"));
    }
}
