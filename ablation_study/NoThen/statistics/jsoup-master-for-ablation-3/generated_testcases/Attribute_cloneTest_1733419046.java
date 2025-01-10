
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attribute_cloneTest {

    @Test
    void testClone() {
        Attribute original = new Attribute("key", "value");
        Attribute cloned = original.clone();

        assertNotSame(original, cloned);
        assertEquals(original.getKey(), cloned.getKey());
        assertEquals(original.getValue(), cloned.getValue());
    }

    @Test
    void testCloneException() {
        Attribute original = new Attribute("key", "value") {
            @Override
            protected Object clone() throws CloneNotSupportedException {
                throw new CloneNotSupportedException();
            }
        };

        RuntimeException exception = assertThrows(RuntimeException.class, original::clone);
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof CloneNotSupportedException);
    }
}
