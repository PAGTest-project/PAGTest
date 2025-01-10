
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_clearAttributesTest {

    @Test
    void testClearAttributes() {
        // Given
        Element element = new Element("div");
        element.attr("class", "test");
        assertNotNull(element.attributes());

        // When
        element.clearAttributes();

        // Then
        assertNull(element.attributes());
    }
}
