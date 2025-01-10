
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_firstElementSiblingTest {

    @Test
    void testFirstElementSibling() {
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");

        parent.appendChild(child1);
        parent.appendChild(child2);

        assertEquals(child1, child1.firstElementSibling());
        assertEquals(child1, child2.firstElementSibling());
    }

    @Test
    void testFirstElementSiblingOrphan() {
        Element orphan = new Element("orphan");
        assertEquals(orphan, orphan.firstElementSibling());
    }
}
