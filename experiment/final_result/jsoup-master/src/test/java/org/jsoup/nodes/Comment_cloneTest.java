
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Comment_cloneTest {

    @Test
    void testClone() {
        Comment original = new Comment("test data");
        Comment cloned = original.clone();

        assertNotSame(original, cloned);
        assertEquals(original.getData(), cloned.getData());
    }
}
