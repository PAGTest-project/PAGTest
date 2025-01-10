
package org.jsoup.nodes;

import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_prependElementTest {

    @Test
    void testPrependElement() {
        Element parent = new Element("parent");
        Element child = parent.prependElement("child", Parser.NamespaceHtml);

        assertEquals("child", child.tagName());
        assertEquals(parent.baseUri(), child.baseUri());
        assertEquals(child, parent.child(0));
    }
}
