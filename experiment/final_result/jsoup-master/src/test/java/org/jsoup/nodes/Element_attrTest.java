
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_attrTest {

    @Test
    void testAttr() {
        Element element = new Element("div");
        element.attr("class", "test");
        assertEquals("test", element.attributes().get("class"));
    }
}
