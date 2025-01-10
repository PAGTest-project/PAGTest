
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Element_rootTest {

    @Test
    void testRoot() {
        Element element = new Element("div");
        assertNotNull(element.root());
    }
}
