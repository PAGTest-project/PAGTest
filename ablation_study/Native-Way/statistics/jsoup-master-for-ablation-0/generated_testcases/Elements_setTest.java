
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_setTest {

    @Test
    void testSet() {
        Elements elements = new Elements();
        Element oldElement = new Element("div");
        Element newElement = new Element("span");

        elements.add(oldElement);

        Element returnedElement = elements.set(0, newElement);

        assertEquals(oldElement, returnedElement);
        assertEquals(newElement, elements.get(0));
    }
}
