
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_attributesTest {

    @Test
    void testAttributesInitialization() {
        Element element = new Element("div");
        assertNull(element.attributes);

        Attributes attributes = element.attributes();
        assertNotNull(attributes);
        assertSame(attributes, element.attributes());
    }
}
