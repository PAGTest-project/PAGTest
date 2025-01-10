
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attribute_cloneTest {

    @Test
    void testClone() {
        Attribute original = new Attribute("key", "value");
        Attribute cloned = original.clone();

        assertEquals(original.getKey(), cloned.getKey());
        assertEquals(original.getValue(), cloned.getValue());
    }
}
