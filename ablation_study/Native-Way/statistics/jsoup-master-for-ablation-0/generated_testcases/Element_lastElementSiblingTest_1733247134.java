
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_lastElementSiblingTest {

    @Test
    void testLastElementSiblingWithParent() {
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        parent.appendChild(child1);
        parent.appendChild(child2);

        assertEquals(child2, child1.lastElementSibling());
    }

    @Test
    void testLastElementSiblingWithoutParent() {
        Element orphan = new Element("orphan");

        assertEquals(orphan, orphan.lastElementSibling());
    }
}
