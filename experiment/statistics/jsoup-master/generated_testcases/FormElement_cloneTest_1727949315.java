
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormElement_cloneTest {

    @Test
    void testClone() {
        // Given
        Tag tag = Tag.valueOf("form");
        String baseUri = "http://example.com";
        Attributes attributes = new Attributes();
        FormElement original = new FormElement(tag, baseUri, attributes);

        // When
        FormElement cloned = original.clone();

        // Then
        assertNotNull(cloned);
        assertNotSame(original, cloned);
        assertEquals(original.tag(), cloned.tag());
        assertEquals(original.baseUri(), cloned.baseUri());
        assertEquals(original.attributes(), cloned.attributes());
    }
}
