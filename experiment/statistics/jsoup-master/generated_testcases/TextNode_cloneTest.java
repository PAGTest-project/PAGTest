
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextNode_cloneTest {

    @Test
    void testClone() {
        TextNode original = new TextNode("test text");
        TextNode cloned = original.clone();

        assertNotSame(original, cloned);
        assertEquals(original.getWholeText(), cloned.getWholeText());
    }
}
