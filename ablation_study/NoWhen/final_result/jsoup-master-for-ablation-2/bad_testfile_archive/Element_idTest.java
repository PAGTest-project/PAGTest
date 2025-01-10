
package org.jsoup.nodes;

import org.jsoup.helper.Validate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_idTest {

    @Test
    void testIdWithNonNullId() {
        Element element = new Element("div");
        element.id("testId");
        assertEquals("testId", element.id());
    }

    @Test
    void testIdWithNullId() {
        Element element = new Element("div");
        assertThrows(IllegalArgumentException.class, () -> element.id(null));
    }
}
