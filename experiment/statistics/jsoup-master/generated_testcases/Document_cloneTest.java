
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Document_cloneTest {

    @Test
    void testClone() {
        // Given
        Document original = new Document("baseUri");
        original.outputSettings().charset("UTF-8");

        // When
        Document cloned = original.clone();

        // Then
        assertNotSame(original, cloned);
        assertEquals(original.outputSettings().charset(), cloned.outputSettings().charset());
    }
}
