
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_lastElementSiblingTest {
    private Element parent;
    private Element child1;
    private Element child2;

    @BeforeEach
    public void setUp() {
        parent = new Element("parent");
        child1 = new Element("child1");
        child2 = new Element("child2");
        parent.appendChild(child1);
        parent.appendChild(child2);
    }

    @Test
    public void testLastElementSiblingWithParent() {
        assertEquals(child2, child1.lastElementSibling());
    }

    @Test
    public void testLastElementSiblingWithoutParent() {
        assertEquals(child1, child1.lastElementSibling());
    }
}
