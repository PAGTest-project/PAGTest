
package org.jsoup.nodes;

import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_prependElementTest {

    @Test
    void testPrependElement() {
        Element parent = new Element("parent");
        Element child = parent.prependElement("child", "namespace");

        assertNotNull(child);
        assertEquals("child", child.tagName());
        assertEquals("namespace", child.tag().namespace());
        assertEquals(parent, child.parent());
        assertEquals(0, parent.elementSiblingIndex());
    }
}
