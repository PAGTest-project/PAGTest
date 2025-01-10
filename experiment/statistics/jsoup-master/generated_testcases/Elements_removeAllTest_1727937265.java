
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Element;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class Elements_removeAllTest {

    @Test
    void testRemoveAll() {
        Elements elements = new Elements(Arrays.asList(
            new Element("div"),
            new Element("span"),
            new Element("p")
        ));

        Collection<?> toRemove = Arrays.asList(
            new Element("span"),
            new Element("p")
        );

        boolean result = elements.removeAll(toRemove);

        assertTrue(result);
        assertEquals(1, elements.size());
        assertEquals("div", elements.get(0).tagName());
    }
}
