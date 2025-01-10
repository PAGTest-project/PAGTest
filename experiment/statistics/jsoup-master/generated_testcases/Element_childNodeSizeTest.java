
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Element_childNodeSizeTest {

    @Test
    void testChildNodeSize() {
        Element element = new Element("div");
        element.ensureChildNodes(); // Ensure child nodes are initialized
        element.appendChild(new TextNode("Text")); // Add a child node

        assertEquals(1, element.childNodeSize()); // Verify the count of child nodes
    }
}
