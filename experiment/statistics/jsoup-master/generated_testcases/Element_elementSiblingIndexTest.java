
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Element_elementSiblingIndexTest {

    @Test
    void testElementSiblingIndex_NoParent() {
        Element element = new Element("div");
        assertEquals(0, element.elementSiblingIndex());
    }

    @Test
    void testElementSiblingIndex_WithParent() {
        Element parent = new Element("div");
        Element child = new Element("span");
        parent.appendChild(child);

        assertEquals(0, child.elementSiblingIndex());
    }
}
